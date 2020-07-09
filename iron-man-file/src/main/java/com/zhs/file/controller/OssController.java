package com.zhs.file.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.AppendObjectRequest;
import com.aliyun.oss.model.AppendObjectResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.zhs.common.IronResult;
import com.zhs.common.enums.FileEnum;
import com.zhs.entity.file.IronFile;
import com.zhs.service.file.IIronFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/5 12:35
 * @package: com.zhs.file
 * @description:
 */
@RestController
@Slf4j
public class OssController {

    @Autowired
    private IIronFileService fileService;


    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accesskeyId}")
    private String accesskeyId;

    @Value("${oss.accesskeySecret}")
    private String accesskeySecret;

    @Value("${oss.bucket}")
    private String bucket;

    @Value("${oss.domian}")
    private String domian;

    @PostMapping("oss-append")
    public IronResult upload(@RequestParam MultipartFile shard,
                             String name,
                             String suffix,
                             Integer size,
                             Integer shardIndex,
                             Integer shardSize,
                             Integer shardTotal,
                             String type,
                             String key1
                          ) throws Exception {
    log.info("上传文件开始");

        FileEnum useEnmu = FileEnum.getByCode(type);
        final String dir = useEnmu.name().toUpperCase();
        String path = dir +File.separator+ key1+"."+suffix;
        //创建OssClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint,accesskeyId,accesskeySecret);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("/text/plain");
        AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucket, path, new ByteArrayInputStream(shard.getBytes()), meta);
        appendObjectRequest.setPosition((long)((shardIndex-1)*shardSize));
        AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);
        System.out.println(appendObjectResult.getObjectCRC());
        ossClient.shutdown();
        //保存文件到本地
        IronFile ironFile = new IronFile();
        ironFile.setPath(path);
        ironFile.setName(name);
        ironFile.setSize(Math.toIntExact(size));
        ironFile.setSuffix(suffix);
        ironFile.setType(type);
        ironFile.setShardIndex(shardIndex);
        ironFile.setShardSize(shardSize);
        ironFile.setShardTotal(shardTotal);
        ironFile.setCreateAt(LocalDateTime.now());
        ironFile.setUpdateAt(LocalDateTime.now());
        ironFile.setId(UUID.randomUUID().toString());
        ironFile.setKey1(key1);
        fileService.save(ironFile);
        String url = domian +path;
        return IronResult.isOk(url);
    }


    @PostMapping("oss-simple")
    public IronResult simpleupload(@RequestParam MultipartFile shard,
                             String name,
                             String suffix,
                             Integer size,
                             Integer shardIndex,
                             Integer shardSize,
                             Integer shardTotal,
                             String type,
                             String key1
    ) throws Exception {
        log.info("上传文件开始");

        FileEnum useEnmu = FileEnum.getByCode(type);
        final String dir = useEnmu.name().toUpperCase();

        String path = dir +File.separator+ key1+"."+suffix;

        //创建OssClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint,accesskeyId,accesskeySecret);
         PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, path, new ByteArrayInputStream(shard.getBytes()));
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        //保存文件到本地
        IronFile ironFile = new IronFile();
        ironFile.setPath(path);
        ironFile.setName(name);
        ironFile.setSize(Math.toIntExact(size));
        ironFile.setSuffix(suffix);
        ironFile.setType(type);
        ironFile.setShardIndex(shardIndex);
        ironFile.setShardSize(shardSize);
        ironFile.setShardTotal(shardTotal);
        ironFile.setCreateAt(LocalDateTime.now());
        ironFile.setUpdateAt(LocalDateTime.now());
        ironFile.setId(UUID.randomUUID().toString());
        ironFile.setKey1(key1);
        fileService.save(ironFile);
        String url = domian +path;
        return IronResult.isOk(url);
    }
}
