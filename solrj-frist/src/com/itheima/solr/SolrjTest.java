package com.itheima.solr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrjTest {
	@Test
	public void addDocument() throws Exception{
	//1.创建一个solr对象，相当于和服务端建立连接，默认第一个都是索引库1（可看做数据库）
	//参数：solr服务器的url,默认是Collection1,需要使用HttpSolrServer类
	SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
	//2.创建一个文档对象,SolrInputDocument
	SolrInputDocument document = new SolrInputDocument();
	//3.向文档中添加域
	// 每个文件中必须有id域
	// 每个域必须在schema.xml中定义。
	document.addField("id","4");
	document.addField("title", "新添加的文件1");
	//4.把文档对象写入索引库
	solrServer.add(document);
	//5.提交
	solrServer.commit();
	}
	@Test
	public void deleteDocument() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
		//不需要创建文档对象之间删除
		//solrServer.deleteById("4");
		//根据查询删除，支持lucene的查询语法,全部删除
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
	@Test
	public void select() throws Exception{
		//创建solr客户端对象
		SolrServer SolrServer = new HttpSolrServer("http://localhost:8080/solr");
		//创建solrQuery对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("*:*");
		//query.set("q", "*:*");
		//执行查询
		QueryResponse response = SolrServer.query(query);
		SolrDocumentList results = response.getResults();
		System.out.println(results.getNumFound());
	}
	
	
	@Test
	public void searchIndexFuza() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
		//创建查询对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("小黄人");
		//过滤条件
		query.addFilterQuery("product_price:[0 TO 10]");
		//参数1：要排序的域 参数2：排序方式
		query.setSort("product_price", ORDER.asc);
		//分页
		query.setStart(0);
		query.setRows(9);
		//默认查询字段
		query.set("df", "product_keywords");
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		//执行查询
		QueryResponse queryResponse = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		//取高亮结果
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		System.out.println("总记录数:" + solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			//取高亮结果
			String name = "";
			List<String> list = highlighting.get(solrDocument.get("id")).get("product_name");
			if (list != null && list.size() > 0) {
				name = list.get(0);
			} else {
				name = (String) solrDocument.get("product_name");
			}
			System.out.println(name);
			System.out.println(solrDocument.get("product_price"));
			System.out.println(solrDocument.get("product_catalog_name"));
			System.out.println(solrDocument.get("product_picture"));
		}
	}
}
