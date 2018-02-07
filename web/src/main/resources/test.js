var settings = {
	"settings": {
		"number_of_shards": 1, //定义一个索引的主分片个数，默认值是 `5`。这个配置在索引创建后不能修改。
		"number_of_replicas": 0, //每个主分片的复制分片个数，默认是 `1`。这个配置可以随时在活跃的索引上修改。
		"analysis": {//用来配置已存在的分析器或创建自定义分析器来定制化你的索引。
			"char_filter": {//定义字符过滤器，字符过滤器是让字符串在被分词前变得更加“整洁”
				"&_to_and": {//将 & 替换成 and，使用一个自定义的 mapping 字符过滤器
					"type": "mapping",
					"mappings": ["&=> and "]
				}
			},
			"filter": {//定义标记过滤器，分词结果的 标记流 会根据各自的情况，传递给特定的标记过滤器。
				"my_stopwords": {
					"type": "stop",//用 stop 标记过滤器去除一些自定义停用词。
					"stopwords": ["the", "a"]
				}
			},
			"analyzer": {//配置分析器，分析器 是三个顺序执行的组件的结合（字符过滤器：char_filter，分词器：tokenizer，标记过滤器：filter）
				"ik": {//分析器别名
					"type": "custom",//分词器类型
					"char_filter": ["html_strip", "&_to_and"],
					"tokenizer": "ik_max_word",//使用ik分词器
					"filter": ["lowercase", "my_stopwords"]
				}
			}
		}
	}
}
