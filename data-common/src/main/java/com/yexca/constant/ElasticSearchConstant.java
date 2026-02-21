package com.yexca.constant;

public class ElasticSearchConstant {
    public static final String INDEX_NAME = "data-element";

    public static final String SEARCH_FIELD_NAME = "all";

    public static final String SORT_FIELD_NAME = "updateTime";

    public static final String MAPPING_TEMPLATE = "{\n" +
            "  “mappings”: {\n" +
            "    “properties”: {\n" +
            "      “dataId”: {\n" +
            "        “type”: “long”,\n" +
            "        “index”: false\n" +
            "      },\n" +
            "\n" +
            "      “userId”: {\n" +
            "        “type”: “long”,\n" +
            "        “index”: false\n" +
            "      },\n" +
            "\n" +
            "      “username”: {\n" +
            "        “type”: “text”,\n" +
            "        “copy_to”: “all”\n" +
            "      },\n" +
            "\n" +
            "      “userRole”: {\n" +
            "        “type”: “integer”,\n" +
            "        “index”: “false”\n" +
            "      },\n" +
            "\n" +
            "      “name”: {\n" +
            "        “type”: “text”,\n" +
            "        “analyzer”: “ik_max_word”,\n" +
            "        “copy_to”: “all”\n" +
            "      },\n" +
            "\n" +
            "      “description”: {\n" +
            "        “type”: “text”,\n" +
            "        “analyzer”: “ik_max_word”,\n" +
            "        “copy_to”: “all”\n" +
            "      },\n" +
            "\n" +
            "      “categoryId”: {\n" +
            "        “type”: “long”,\n" +
            "        “index”: false\n" +
            "      },\n" +
            "\n" +
            "      “categoryName”: {\n" +
            "        “type”: “text”,\n" +
            "        “analyzer”: “ik_max_word”,\n" +
            "        “copy_to”: “all”\n" +
            "      },\n" +
            "\n" +
            "      “sampleFileLink”: {\n" +
            "        “type”: “text”,\n" +
            "        “index”: false\n" +
            "      },\n" +
            "\n" +
            "      “fileLink”: {\n" +
            "        “type”: “text”,\n" +
            "        “index”: false\n" +
            "      },\n" +
            "\n" +
            "      “createTime”: {\n" +
            "        “type”: “date”,\n" +
            "        “index”: false\n" +
            "      },\n" +
            "\n" +
            "      “updateTime”: {\n" +
            "        “type”: “date”,\n" +
            "        “index”: false\n" +
            "      },\n" +
            "\n" +
            "      “all”: {\n" +
            "        “type”: “text”,\n" +
            "        “analyzer”: “ik_max_word”\n" +
            "      },\n" +
            "\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
