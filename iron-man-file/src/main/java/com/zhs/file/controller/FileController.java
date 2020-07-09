package com.zhs.file.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.common.IronResult;
import com.zhs.common.enums.FileEnum;
import com.zhs.entity.file.IronFile;
import com.zhs.service.file.IIronFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class FileController {

    @Autowired
    private IIronFileService fileService;

    @Value("${file.domain}")
    private String FILE_DOMAIN;

    @Value("${file.path}")
    private String FILE_PATH;

    @PostMapping("upload")
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
        //final List<IronFile> list = fileService.list();
        //return list;
    log.info("上传文件开始");

     FileEnum useEnmu = FileEnum.getByCode(type);
     final String dir = useEnmu.name().toUpperCase();
     File fullDir = new File(FILE_PATH + dir);
     if(!fullDir.exists()){
         fullDir.mkdir();
     }
     String path = dir +File.separator+ key1+"."+suffix;
     String localPath = path+"."+shardIndex;
     log.info("当前的位置"+localPath);
     String fullPath = FILE_PATH+localPath;
     File dest = new File(fullPath);
     shard.transferTo(dest);
     log.info(dest.getAbsolutePath());

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
        if(shardIndex.equals(shardTotal) ){
            this.merge(ironFile);
        }
        String url = FILE_DOMAIN +path;
        return IronResult.isOk(url);
    }

    public void merge(IronFile ironFile) throws Exception {
         String path = ironFile.getPath();
         Integer shardTotal = ironFile.getShardTotal();
        File newFile = new File(FILE_PATH+path);
        FileOutputStream outputStream = new FileOutputStream(newFile,true);
        FileInputStream fileInputStream = null;
        byte[] bytes = new byte[10*1024*1024];
        int len ;
        try{
            for (int i = 0; i <shardTotal ; i++) {
                fileInputStream = new FileInputStream(new File(FILE_PATH+path+"."+(i+1)));
                while ((len= fileInputStream.read(bytes))!=-1){
                    outputStream.write(bytes,0,len);
                }
            }
        }catch (IOException e){
            log.error("分片合并异常",e);
        }finally {
            try{
                if(fileInputStream !=null){
                    fileInputStream.close();
                }
                outputStream.close();
            }catch (Exception e){
                log.error("IO关闭异常");
            }
        }
        log.info("上传分片结束");

        System.gc();
         Thread.sleep(100);
        log.info("删除分片开始");
        for (int i =0;i<shardTotal;i++){
             String filePath = FILE_PATH + path + "." + (i + 1);
             File file = new File(filePath);
             boolean result = file.delete();
             log.info("删除{}，{}",filePath,result?"成功":"失败");
        }
        log.info("删除分片结束");
    }

    @GetMapping("check/{key}")
    public IronResult check(@PathVariable String key){
        log.info("检查上传分片开始{}",key);
        final QueryWrapper<IronFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("key1",key);
        final IronFile entity = fileService.getOne(queryWrapper);
        return IronResult.isOk(entity);
    }
//    @PostMapping("upload")
//    public IronResult upload(@RequestBody FileDto fileDto) throws IOException {
//        //final List<IronFile> list = fileService.list();
//        //return list;
//        log.info("上传文件开始");
//
//        String type = fileDto.getType();
//        String suffix = fileDto.getSuffix();
//        String shardbase64 = fileDto.getShard();
//        final MultipartFile shard = BASE64DecodedMultipartFile.base64ToMultipart(shardbase64);
//        FileEnum useEnmu = FileEnum.getByCode(type);
//        String newName = UUID.randomUUID().toString().substring(1,6);
//
//
//        final String dir = useEnmu.name().toUpperCase();
//        File fullDir = new File(FILE_PATH + dir);
//        if(!fullDir.exists()){
//            fullDir.mkdir();
//        }
//
//        String path = dir +File.separator+ newName+"."+suffix;
//        String fullPath = FILE_PATH+path;
//        File dest = new File(fullPath);
//        shard.transferTo(dest);
//        log.info(dest.getAbsolutePath());
//
//        //保存文件到本地
//
//        IronFile ironFile = new IronFile();
//        BeanUtils.copyProperties(fileDto,ironFile);
//        ironFile.setPath(path);
//        ironFile.setCreateAt(LocalDateTime.now());
//        ironFile.setUpdateAt(LocalDateTime.now());
//        ironFile.setId(UUID.randomUUID().toString());
//        fileService.save(ironFile);
//
//        String url = FILE_DOMAIN +path;
//        return IronResult.isOk(url);
//    }

    @GetMapping("/download")
    public void down(HttpServletRequest request, HttpServletResponse response,@RequestHeader(required = false)String range){
        File file = new File("D:\\file\\iron\\man\\COURSE\\BBB.mp4");

        //开始下载位置
        long startByte = 0;
        long endByte = file.length()-1;
        if(range!=null && range.contains("bytes=")&&range.contains("-")){
            //http状态吗要为206
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            range = range.substring(range.lastIndexOf("=")+1).trim();

            String[] ranges = range.split("-");
            try {
                   if(range.length()==1){
                       if(range.startsWith("-")){
                           endByte = Long.parseLong(ranges[0]);
                       }else if(range.endsWith("-")){
                           startByte = Long.parseLong(ranges[0]);
                       }
                   }else  if(ranges.length==2){
                       startByte = Long.parseLong(ranges[0]);
                       endByte =Long.parseLong(ranges[1]);
                   }
            }catch (NumberFormatException e){
                startByte = 0;
                endByte = file.length()-1;
            }
        }else{
            //没有range即全部一次性传输 需要用200状态吗
            response.setStatus(HttpServletResponse.SC_OK);
        }
        //要下载的长度(endByte为总场地-1  这时候要加回去
        long contentLength = endByte - startByte+1;
        //文件名称
         String fileName = file.getName();

        final String contentType = request.getServletContext().getMimeType(fileName);
        response.setHeader("Accept-Ranges","bytes");
        response.setContentType(contentType);
        response.setHeader("Content-Type",contentType);
        //response.setHeader("Content-Disposition","inline;filename=test.mp4");
        response.setHeader("Content-length",String.valueOf(contentLength));
        // [要下载的开始位置]-[结束位置]/[文件总大小]
        response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + file.length());
        BufferedOutputStream outputStream = null;
        RandomAccessFile randomAccessFile = null;
        //已传送数据大小
        long transmitted = 0;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[4096];
            int len = 0;
            randomAccessFile.seek(startByte);
            //坑爹地方四：判断是否到了最后不足4096（buff的length）个byte这个逻辑（(transmitted + len) <= contentLength）要放前面！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
            //不然会先读取randomAccessFile，造成后面读取位置出错，找了一天才发现问题所在
            while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                transmitted += len;
                //停一下，方便测试（本地下载传输速度特别快，没反应过来就下载好了），实际生产环境中用的时候需要删除这一行
                Thread.sleep(10);
            }
            //处理不足buff.length部分
            if (transmitted < contentLength) {
                len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                outputStream.write(buff, 0, len);
                transmitted += len;
            }

            outputStream.flush();
            response.flushBuffer();
            randomAccessFile.close();
            System.out.println("下载完毕：" + startByte + "-" + endByte + "：" + transmitted);

        } catch (ClientAbortException e) {
            System.out.println("用户停止下载：" + startByte + "-" + endByte + "：" + transmitted);
            //捕获此异常表示拥护停止下载
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("upload-test")
    public IronResult upload(@RequestParam MultipartFile file,
                             String name,
                             String suffix,
                             Integer size,

                             String type,
                             String key1
    ) throws Exception {
        log.info("上传文件开始");
        FileEnum useEnmu = FileEnum.getByCode(type);
        final String dir = useEnmu.name().toUpperCase();
        File fullDir = new File(FILE_PATH + dir);
        if(!fullDir.exists()){
            fullDir.mkdir();
        }
        String path = dir +File.separator+ key1+"."+suffix;
        String fullPath = FILE_PATH+path;
        File dest = new File(fullPath);
        file.transferTo(dest);
        log.info(dest.getAbsolutePath());

        //保存文件到本地
        IronFile ironFile = new IronFile();
        ironFile.setPath(path);
        ironFile.setName(name);
        ironFile.setSize(Math.toIntExact(size));
        ironFile.setSuffix(suffix);
        ironFile.setType(type);
        ironFile.setCreateAt(LocalDateTime.now());
        ironFile.setUpdateAt(LocalDateTime.now());
        ironFile.setId(UUID.randomUUID().toString());
        ironFile.setKey1(key1);
        fileService.save(ironFile);
        String url = FILE_DOMAIN +path;
        return IronResult.isOk(url);
    }

}
