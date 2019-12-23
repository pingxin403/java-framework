package com.hyp.learn.ll;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Iterator;


/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ll
 * hyp create at 19-12-20
 **/
public class Maintest {
    public static void main(String[] args) throws Exception {

//        indexDir();

        searchIndex();

//        testTokenStream();
    }

    public static void indexString() throws IOException {
        //1 创建文档对象
        Document document = new Document();
        // 创建并添加字段信息。参数：字段的名称、字段的值、是否存储，这里选Store.YES代表存储到文档列表。Store.NO代表不存储
        document.add(new StringField("id", "1", Field.Store.YES));
        // 这里我们title字段需要用TextField，即创建索引又会被分词。StringField会创建索引，但是不会被分词
        document.add(new TextField("title", "谷歌地图之父跳槽facebook", Field.Store.YES));

        //2 索引目录类,指定索引在硬盘中的位置

        Directory directory = FSDirectory.open(Paths.get("/home/hyp/dev/lucene"));
        //3 创建分词器对象
        Analyzer analyzer = new StandardAnalyzer();
        //4 索引写出工具的配置对象
        IndexWriterConfig conf = new IndexWriterConfig(analyzer);
        //5 创建索引的写出工具类。参数：索引的目录和配置信息
        IndexWriter indexWriter = new IndexWriter(directory, conf);

        //6 把文档交给IndexWriter
        indexWriter.addDocument(document);
        //7 提交
        indexWriter.commit();
        //8 关闭
        indexWriter.close();
    }


    public static void indexDir() throws IOException {

        //1 索引目录类,指定索引在硬盘中的位置
        FSDirectory directory = FSDirectory.open(Paths.get("/home/hyp/dev/lucene"));
        //2 创建分词器对象
        Analyzer analyzer = new IKAnalyzer();
        //3 索引写出工具的配置对象
        IndexWriterConfig conf = new IndexWriterConfig(analyzer);
        //4 创建索引的写出工具类。参数：索引的目录和配置信息
        IndexWriter indexWriter = new IndexWriter(directory, conf);

        //5 读取磁盘上文件
        File dir = new File("/media/hyp/blog/article/");

        Iterator<File> fileIterator = FileUtils.iterateFiles(dir, null, false);

        while (fileIterator.hasNext()) {

            File file = fileIterator.next();

            String fileName = file.getName();

            String path = file.getPath();
            String content = FileUtils.readFileToString(file, Charset.forName("utf-8"));
            long size = FileUtils.sizeOf(file);

            //创建Field

            //域的名称；域的内容；是否存储
            Field fieldName = new TextField("name", fileName, Field.Store.YES);
            Field fieldPath = new StoredField("path", path);
            Field fieldContent = new TextField("content", content, Field.Store.YES);
            Field fieldSizeValue = new LongPoint("size", size);
            Field fieldSizeStore = new StoredField("size", size);

            //创建文档对象
            Document document = new Document();
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContent);
            document.add(fieldSizeValue);
            document.add(fieldSizeStore);

            //6 把文档对象写入索引库
            indexWriter.addDocument(document);
        }

        //7 提交
        indexWriter.commit();
        //8 关闭
        indexWriter.close();

    }

    public static void searchIndex() throws Exception {
        //1 索引目录类,指定索引在硬盘中的位置
        FSDirectory directory = FSDirectory.open(Paths.get("/home/hyp/dev/lucene"));
        //2 创建IndexReader对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //3 创建indexSearcher对象，构造参数为IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        QueryParser queryParser = new QueryParser("content", new IKAnalyzer());

        //4 创建一个Query对象
        Query query =queryParser.parse( "import");
        //5. 执行查询，得到一个TopDocs对象
        TopDocs topDocs = indexSearcher.search(query, 10);
        //6 查询结果总数
        System.out.println("查询总记录数" + topDocs.totalHits);
        //7 取文档列表
        ScoreDoc[] docs = topDocs.scoreDocs;

        //8 打印文档内容

        for (ScoreDoc doc : docs) {
            //文档ID
            int id = doc.doc;

            Document document = indexSearcher.doc(id);

            System.out.println(document.get("name"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
            System.out.println("-----------");

        }

        //9 关闭indexReader对象
        indexReader.close();

    }

    /**
     * 测试 TokenStream的使用
     *
     * @throws Exception
     */
    public static void testTokenStream() throws Exception {
        //1 创建Analyzer对象
//        Analyzer analyzer = new StandardAnalyzer();
        IKAnalyzer ikAnalyzer = new IKAnalyzer();
        //2. 使用分析器获取TokenStream对象
        TokenStream tokenStream = ikAnalyzer.tokenStream("", "谷歌地图之父跳槽facebook");
        //3 向tokenStream对象设置一个引用
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //4 调用tokenStream的reset方法
        tokenStream.reset();
        //5 遍历tokenStream对象
        while (tokenStream.incrementToken()) {
            System.out.println(charTermAttribute.toString());
        }

        //6 关闭tokenStream
        tokenStream.close();

    }


    public static void testSearch(String key) throws IOException, ParseException {
        //1 创建读取目录对象
        //2 创建索引读取工具
        //3 创建索引搜索工具
        //4 创建查询解析器
        //5 创建查询对象
        //6 搜索数据
        //7 各种操作
        // 索引目录对象
        Directory directory = FSDirectory.open(Paths.get("/home/hyp/dev/lucene"));
        // 索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        // 索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);

        // 创建查询解析器,两个参数：默认要查询的字段的名称，分词器
        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        // 创建查询对象
        Query query = parser.parse(key);

        // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
        // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
        TopDocs topDocs = searcher.search(query, 10);
        // 获取总条数
        System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");
        // 获取得分文档对象（ScoreDoc）数组.SocreDoc中包含：文档的编号、文档的得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 取出文档编号
            int docID = scoreDoc.doc;
            // 根据编号去找文档
            Document doc = reader.document(docID);
            System.out.println("id: " + doc.get("id"));
            System.out.println("title: " + doc.get("title"));
            // 取出文档得分
            System.out.println("得分： " + scoreDoc.score);
        }

    }

    public void search(Query query) throws Exception {
        // 索引目录对象
        Directory directory = FSDirectory.open(Paths.get("/home/hyp/dev/lucene"));
        // 索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        // 索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);

        // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
        // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
        TopDocs topDocs = searcher.search(query, 10);
        // 获取总条数
        System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");
        // 获取得分文档对象（ScoreDoc）数组.SocreDoc中包含：文档的编号、文档的得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for (ScoreDoc scoreDoc : scoreDocs) {
            // 取出文档编号
            int docID = scoreDoc.doc;
            // 根据编号去找文档
            Document doc = reader.document(docID);
            System.out.println("id: " + doc.get("id"));
            System.out.println("title: " + doc.get("title"));
            // 取出文档得分
            System.out.println("得分： " + scoreDoc.score);
        }
    }

    /*
     * TermQuery普通词条查询
     * 注意：Term(词条)是搜索的最小单位，不可再分词。值必须是字符串！
     * 场景：如果一个字段不需要分词，如id
     * Lucene中term要求必须为字符串
     */
    public void testTermQuery() throws Exception {
        // 创建词条查询对象
        Query query = new TermQuery(new Term("title", "谷歌地图"));
        search(query);
    }

    /*
     * 测试通配符查询
     * 	? 可以代表任意一个字符
     * 	* 可以任意多个任意字符
     */
    public void testWildCardQuery() throws Exception {
        // 创建查询对象
        Query query = new WildcardQuery(new Term("title", "*歌*"));
        search(query);
    }

    /*
     * 测试模糊查询
     */
    public void testFuzzyQuery() throws Exception {
        // 创建模糊查询对象:允许用户输错。但是要求错误的最大编辑距离不能超过2
        // 编辑距离：一个单词到另一个单词最少要修改的次数 facebool --> facebook 需要编辑1次，编辑距离就是1
//    Query query = new FuzzyQuery(new Term("title","fscevool"));
        // 可以手动指定编辑距离，但是参数必须在0~2之间
        Query query = new FuzzyQuery(new Term("title", "facevool"), 1);
        search(query);
    }

//    /*
//     * 测试：数值范围查询
//     * 注意：数值范围查询，可以用来对非String类型的ID进行精确的查找
//     */
//    public void testNumericRangeQuery() throws Exception{
//        // 数值范围查询对象，参数：字段名称，最小值、最大值、是否包含最小值、是否包含最大值
//        Query query = NumericRangeQuery.newLongRange("id", 2L, 2L, true, true);
//
//        search(query);
//    }
//
//    /*
//     * 布尔查询：
//     * 	布尔查询本身没有查询条件，可以把其它查询通过逻辑运算进行组合！
//     * 交集：Occur.MUST + Occur.MUST
//     * 并集：Occur.SHOULD + Occur.SHOULD
//     * 非：Occur.MUST_NOT
//     */
//    public void testBooleanQuery() throws Exception{
//
//
//        Query query1 = NumericRangeQuery.newLongRange("id", 1L, 3L, true, true);
//        Query query2 = NumericRangeQuery.newLongRange("id", 2L, 4L, true, true);
//
//        // 创建布尔查询的对象
//        BooleanQuery query = new BooleanQuery();
//        // 组合其它查询
//        query.add(query1, BooleanClause.Occur.MUST_NOT);
//        query.add(query2, BooleanClause.Occur.SHOULD);
//
//        search(query);
//    }


}
