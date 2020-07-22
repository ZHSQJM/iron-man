import com.zhs.ElasticSearchApplication;

import com.zhs.es.entity.SkuInfoEs;
import com.zhs.es.repositroy.SkuInfoEsRepository;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Book;
import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/17 13:19
 * @package: PACKAGE_NAME
 * @description:
 */



@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchApplication.class)
public class EsTest {

//    @Autowired
//    private SkuInfoRepositroy skuInfoRepositroy;

//
//    @Autowired
//    private JestClient jestClient;

//    @Test
//    public void test01() throws IOException {
//
//        final List<SkuInfo> all = skuInfoRepositroy.findAll();
//        System.out.println(all);
//
////        for (SkuInfo skuInfo : all) {
////            final Index index = new Index.Builder(skuInfo).index("gmall").type("_doc").id(String.valueOf(skuInfo.getId())).build();
////            jestClient.execute(index);
////        }
//    }

//    @Test
//    public void test02() throws IOException {
//
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(10);
//
//        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
//        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("spuId","1");
//        queryBuilder.filter(termQueryBuilder);
//        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuDesc","华为");
//        queryBuilder.must(matchQueryBuilder);
//        searchSourceBuilder.query(queryBuilder);
//
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//
//        highlightBuilder.preTags("<span style='color:red'>");
//        highlightBuilder.postTags("</span>");
//        highlightBuilder.field("skuDesc",12);
//        searchSourceBuilder.highlighter(highlightBuilder);
//        final String dsl = searchSourceBuilder.toString();
//        System.out.println(dsl);
//        final Search search = new Search.Builder(dsl).addIndex("gmall").build();
//        final SearchResult execute = jestClient.execute(search);
//        final List<SearchResult.Hit<SkuInfo, Void>> hits = execute.getHits(SkuInfo.class);
//        List<SkuInfo> list = new ArrayList<>();
//        for (SearchResult.Hit<SkuInfo, Void> hit : hits) {
//            final SkuInfo source = hit.source;
//            final List<String> list1 = hit.highlight.get("skuDesc");
//            System.out.println(list1);
//            System.out.println(source);
//        }
//    }


    @Autowired
    private SkuInfoEsRepository skuInfoEsRepository;

    @Test
    public void createIndex2(){

        SkuInfoEs skuInfo = new SkuInfoEs();
        skuInfo.setId(1L);
        skuInfo.setPrice(200d);
        skuInfo.setSkuName("小米手机");
        skuInfo.setSkuDefaultImg("手机手机");
        skuInfoEsRepository.index(skuInfo);
    }
    @Test
    public void useFind() {
        SkuInfoEs  skuInfoEs = skuInfoEsRepository.findById( 1L).get();
        System.out.println(skuInfoEs);

    }


}
