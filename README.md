# **司法大数据自动化标注与分析说明文档**

# **使用说明**

## **运行方式：**

1. （推荐）用户访问 [`121.40.140.239:8080`](http://121.40.140.239:8080) 在线运行
2. 用户运行打包后 jar 文件 `WenshuApplication-Release-1.0.0`
3. 用户运行源码中的 `WenshuApplication` 类

若本地运行，则浏览器输入 `localhost:8080` 即可登入页面。整个页面总体分为三个板块，分别为文书部分，标记部分和导出部分。

## **文书部分**

用户可在手动输入框中输入案件信息或者上传本地 txt 文件。文本框中存在案件内容后点击分词，系统即可对文本内容进行自动化分词，并将分词结果作为标记部分的选项以供用户选择。同时提供”清空文本框”按钮以便更换文书内容。

## **标记部分**

用户可以根据所需要的基本信息选择姓名、性别、民族、出生地、案由及法院，同时页面支持手动添加所需信息，手动输入信息后点击“确定添加”即可显示在下拉框中。

## **导出部分**

用户可以点击“导出文本”下载文书文件，格式为“.txt”；点击“导出标注”下载标注文件，格式为”.json“。（注意：若此文书是通过本地文件上传，则文本和标注文件的默认名均为原本地文件名；若此文书是手动输入，则文本和标注文件的默认名为文书第一行内容）



# **理解**

司法领域中在处理文档内容时会遇到各种问题，如：文档录入繁琐，文书检索困难，文书阅读效率低，文书内容不便归纳统计等。

当今时代是大数据的时代，大数据的强大功能体现在方方面面，信息的处理与分类能帮助人们加深对信息的理解以及更好地应用于生产生活中。受到智慧法院的启发，研究司法大数据将会更有效地处理案件文书，极大便利用户。借助信息技术，我们可以快速地标记法律文书中的关键信息，生成对应的标记文本；借助标记文本，我们可以实现对大量文书统筹归纳，快速统计等功能。可见法律文书的自动化标记拥有者重要的意义和作用。

利用信息技术，实现智慧法院也是最高法指导各级法院建设的重要内容；在信息技术的加持下，智慧法院可以实现，网上办理、信息、流程公开，智能服务等特性，提高法院办事效率，提升法院服务水平；

基于以上司法大数据的优势，我们小组初步实现了为用户提供美观易用的前端交互界面，并收集海量原始文书数据，对其进行关键信息的半自动化标注，用户可以输入文书信息，或使用实时爬虫系统爬取文书。原始数据经预处理后返回关键词信息，以供进一步手动标注。我们小组希望通过这样一个系统帮助人们更好地处理司法数据，将繁琐的信息精简化，关键化，如若日后功能更加完善，就有望对案件审判与司法数据查询带去更大的帮助。



# **基本思路**

1. 收集海量原始文书数据，对其进行关键信息的半自动化标注。包括各个当事人信息、案由和法院的信息提取。
2. 为用户提供美观且易用的前端交互界面。用户可以选择输入文书信息，或者上传txt文件。原始文书数据经过后端程序预处理后返回分类别的关键词信息，以供用户进一步手动进行精确标注。
3. 后端对文书内容进行分词与预处理
4. 使用框架构建前后端交互的一整套系统，并部署在云服务器上，方便用户快速地访问与使用。



# **实现细节**



## **项目框架**

### 整体使用 Springboot + Vue 框架

技术优势：

1）前后端解耦，各自职责明确，分工合理，开发效率较高。

2）减轻服务器压力，各自配置，各自运作，更容易维护。

3）项目模块化更明显，业务逻辑更加清晰，不至于等到项目规模过大的时候无法维护或者更新项目。

4）方便单元测试以及bug调试，互不相干，前后端的人技术职责分开，减少前后端的人打架的概率，技术成本更少。



## **前端** 

- ### **ElementUI 组件套**

Element UI是一套为开发者、设计师和产品经理准备的基于 Vue 2.0 的桌面端组件库，包含了一套设计原则、组件和组件。前端的页面我们全部采用了 Element UI ，使得页面布局美观简洁，努力给用户极佳的视觉体验。

- ### **Vue.js 渐进式框架**

前端代码采用当下流行的 vue 框架，易于上手的同时也能很好地与现代化的工具链以及各种支持类库结合使用。

- ### **Axios HTTP 库**

在前后端交互时，我们采用了 Axios 这一基于 promise 的 HTTP 库，它是一个适用于 vue 框架的轻量级库。Axios 可以在浏览器中发送 XMLHttpRequests，可以在 node.js 发送 http 请求，还可以自动转换 JSON 数据等等。

- ### **FileSaver.js 文件导出插件**

文件的导出我们选用了 FileSaver.js 这一著名的开源前端文件下载项目，它是基于 Blob 进行下载的，但也正是完全基于前端的下载功能，使得浏览器下载有一定的限制，即Blob数据的大小不能过大。根据官网给的参数，大多数支持的浏览器数据大小可以达到 500MB+，我们认为足够适用于当前的项目，至于未来若有更大的数据大小要求，可以使用官网给出的替代方案StreamSaver 来解决。

## **后端**

- ### **Springboot 框架**

Spring Boot 是用来简化新 Spring 应用的初始搭建以及开发过程的一个框架。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置，简化了开发流程，提高开发效率。

- ### **HanLP 自然语言处理包**

HanLP是一系列模型与算法组成的NLP工具包，主要功能包括分词、词性标注、关键词提取、自动摘要、依存句法分析、命名实体识别、短语提取、拼音转换、简繁转换等等。

在本项目中，我们主要使用 HanLP 提供的 Restful API 进行文本的初步分词。此外，我们对于地名的分词结果进行了一些简单的优化，合并相邻的、过短的地点；我们还手动增加了罪名词典（来源：https://www.zuiming.net/51.html）以便精确判断罪名。



## **项目管理**

使用 NJU GitLab 进行代码管理和多人合作。

地址：https://git.nju.edu.cn/HC_Plantern/DataScienceProject