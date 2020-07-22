ElasticSearch 安装 
JDK1.8最低要求 
下载地址
https://mirrors.huaweicloud.com/elasticsearch/7.6.2/?C=N&O=D
解压即可
/bin 启动文件
config 配置文件
 log4j2日志配置
 jvm.options java虚拟机配置
 elasticsearch.yml elasticsearch的配置文件
lib:相关jar包
module 功能模块
启动: .bat
访问 127.0.0.1:9200

可视化界面 head插件
node环境
git clone git://github.com/mobz/elasticsearch-head.git
cd elasticsearch-head
npm install
npm run start
open http://localhost:9100/

访问head。填写elasticsearch的安装地址
会发现跨域错误
解决
elasticsearch.yml
```yaml
http.cors.enabled: true
http.cors.allow-origin: "*"
```

新建索引  ---对应数据库

kibana 
需要node
bin/bat

127.0.0.1:5601
修改kibana的汉化
kibana.yml的配置文件 i18n.locale:"zh-CN"





elasticsearch核心概念
数据库---索引
表---types
行---documents
字段-- fileds



IK分词器（中文）
https://github.com/medcl/elasticsearch-analysis-ik/releases/tag/v7.6.2
下载完毕后直接放入到elasticsearch的插件管理中
mvn clean
mvn compile
mvn package
然后重启

elasticsearch-plugin 可以通过这个命令来查找 elasticsearch list
使用kibana测试ik分词器

ik分词器2种分词算法
ik_smart :最少切分
GET _analyze
{
  "analyzer":"ik_smart",
  "text":["中国共产党"]
}
不可能有重复的词在一起
ik_max_word 最细粒度划分
GET _analyze
{
  "analyzer":"ik_max_word",
  "text": ["中国共产党"]
}
---------------------------------------
GET _analyze
{
  "analyzer":"ik_max_word",
  "text": ["周华生"]
}
{
  "tokens" : [
    {
      "token" : "周",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "华生",
      "start_offset" : 1,
      "end_offset" : 3,
      "type" : "CN_WORD",
      "position" : 1
    }
  ]
}

周华生被差开来了
自己需要的词，需要自己加到我们的分词器的字典中
ik分词器配置自己的分词
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
	<comment>IK Analyzer 扩展配置</comment>
	<!--用户可以在这里配置自己的扩展字典 -->
	<entry key="ext_dict">zhs.dic</entry>
	 <!--用户可以在这里配置自己的扩展停止词字典-->
	<entry key="ext_stopwords"></entry>
	<!--用户可以在这里配置远程扩展字典 -->
	<!-- <entry key="remote_ext_dict">words_location</entry> -->
	<!--用户可以在这里配置远程扩展停止词字典-->
	<!-- <entry key="remote_ext_stopwords">words_location</entry> -->
</properties>
 创建一个zhs.dic 然后在里面写上周华生 在配置 重新启动
 
 再次请求
 {
   "tokens" : [
     {
       "token" : "周华生",
       "start_offset" : 0,
       "end_offset" : 3,
       "type" : "CN_WORD",
       "position" : 0
     }
   ]
 }

 
RESTFUL  
PUT /索引名/types/文档ID
数据库/表/数据
PUT /zhs/types/1
{
  "name":"zhs",
  "age":"12"
}
那么name这个字段用不用指定类型尼，毕竟我们关系型数据库十需要指定类型的
字符串类型
text,keyword

数值类型
long integer short byte double float half——float s  caled——float

创建索引指定类型
PUT /test2
{
  "mappings": {
    "properties": {
      "name":{
        "type":"text"
      },
      "age":{
        "type": "long"
      },
      "birthday":{
        "type": "date"
      }
    }
  }
}
同一个索引只能建立一个types
如果自己的文档字段没有指定，那么es就会给默认配置字段类型


索引/表/_search?q=name:狂神说

复杂查询 （排序 分页 高亮 模糊查询）

_source:["",""]


倒排索引：：

PUT test_index
{
  "settings":{
    "number_of_shards":1, 
    "number_of_replicas":0     
  },
  "mappings":{
    "product": {
      "properties": {
        "title": {"type": "text"}
      }
    }
  }
}

*****product为指定索引类型

ES7必须改为：

PUT test_index
{
  "settings":{
    "number_of_shards":1, 
    "number_of_replicas":0     
  },
  "mappings":{
      "properties": {
        "title": {"type": "text"}
    }
  }
}


springboot 集成时候需要注意把那本
2.0.* 版本用5.5.*的es
2.1.* 用6.2
2.2用7.6的