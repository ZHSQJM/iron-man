//package com.zhs.file.utils;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import com.aliyun.oss.model.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * @project: iron-man
// * @author: zhs
// * @date: 2020/7/23 13:33
// * @package: com.zhs.file.utils
// * @description:
// */
//@Component
//public class BigFileUploadUtils {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//
//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint;
//    @Value("${aliyun.oss.accessKeyId}")
//    private String accessKeyId;
//    @Value("${aliyun.oss.accessKeySecret}")
//    private String accessKeySecret;
//    @Value("${aliyun.oss.domain}")
//    private String prefix;
//    @Value("${aliyun.oss.bucketName}")
//    private String bucketName;
//
//    private static OSS ossClient = null;
//
//    /**
//     * 上初始化大文件上传环境，返回uploadId
//     *
//     * @param objectName 文件
//     * @return 返回uploadId
//     */
//    public String initUpload(String objectName) {
//        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        // 创建InitiateMultipartUploadRequest对象。
//        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, objectName);
//        // 如果需要在初始化分片时设置文件存储类型，请参考以下示例代码。
//        // ObjectMetadata metadata = new ObjectMetadata();
//        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//        // request.setObjectMetadata(metadata);
//        // 初始化分片。
//        InitiateMultipartUploadResult upresult = ossClient.initiateMultipartUpload(request);
//        // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个uploadId发起相关的操作，如取消分片上传、查询分片上传等。
//        String uploadId =  upresult.getUploadId();
//        //将uploadId缓存到redis,2个小时有效
//        redisCache.setCacheObject("uploadId:"+uploadId, objectName, 120*60*1000, TimeUnit.MINUTES);
//        return uploadId;
//    }
//    /**
//     * 上传指定的文件片断，返回uploadId
//     *
//     * @param uploadId 文件ID
//     * @return chunk的MD5
//     */
//    public String uploadChunk(String uploadId,
//                              Integer chunkId,
//                              MultipartFile file) throws IOException {
//        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        InputStream instream = file.getInputStream();
//        long curPartSize = file.getSize();
//        String objectName = redisCache.getCacheObject("uploadId:"+uploadId);
//        UploadPartRequest uploadPartRequest = new UploadPartRequest();
//        uploadPartRequest.setBucketName(bucketName);
//        uploadPartRequest.setKey(objectName);
//        uploadPartRequest.setUploadId(uploadId);
//        uploadPartRequest.setInputStream(instream);
//        // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100 KB。
//        uploadPartRequest.setPartSize(curPartSize);
//        // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
//        uploadPartRequest.setPartNumber(chunkId);
//        System.out.println(uploadPartRequest);
//        System.out.println(ossClient);
//        // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
//        UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
//        // 每次上传分片之后，OSS的返回结果包含PartETag。PartETag将被保存在partETags中。
//        PartETag partETag = uploadPartResult.getPartETag();
//        //需要将PartETag缓存存起来，如果是第一个块，需要先将创建list，以便add
//        List<PartETag> partETagList = redisCache.getCacheObject("partETags:"+uploadId);
//        if (partETagList == null){
//            partETagList = new ArrayList<PartETag>();
//        }
//        partETagList.add(partETag);
//        redisCache.setCacheObject("partETags:"+uploadId, partETagList,120*60*1000, TimeUnit.MINUTES);
//        System.out.println(partETag);
//        String md5Str = partETag.getETag();
//        return md5Str;
//    }
//    /**
//     * 合并文件，返回文件URL
//     *
//     * @param uploadId 文件
//     * @return 返回文件返回URL
//     */
//    public String completeFile(String uploadId) {
//        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        //获取该uploadId缓存的信息和各块的信息，
//        String objectName = redisCache.getCacheObject("uploadId:"+uploadId);
//        List<PartETag> partETagList = redisCache.getCacheObject("partETags:"+uploadId);
//        // 创建CompleteMultipartUploadRequest对象。
//        // 在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
//        CompleteMultipartUploadRequest completeMultipartUploadRequest =
//                new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETagList);
//        // 如果需要在完成文件上传的同时设置文件访问权限，请参考以下示例代码。
//        // completeMultipartUploadRequest.setObjectACL(CannedAccessControlList.PublicRead);
//        // 完成上传。
//        CompleteMultipartUploadResult completeMultipartUploadResult = ossClient.completeMultipartUpload(completeMultipartUploadRequest);
//        System.out.println(completeMultipartUploadResult);
//        // 关闭OSSClient，删除redis缓存的内容。
//        ossClient.shutdown();
//        redisCache.deleteObject("uploadId:"+uploadId);
//        redisCache.deleteObject("partETags:"+uploadId);
//        return completeMultipartUploadResult.getLocation();
//    }
//}
