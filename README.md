# ComposeLearn
Jatpack Compose学习
目标：实现一个猜单词游戏。使用ViewModel、StateFlow、State管理游戏状态，实现游戏功能函数；使用Room存储单词，实现查询和新增功能；使用Navigation实现游戏页面和数据库页面跳转。

## 项目结构
MainActivity：主入口 \\
**ui** \\
- GameScreen：游戏屏幕
- GameViewModel：管理游戏数据和状态，游戏功能函数也实现在里面
- GameUiState：状态辅助类，在GameViewModel使用MutableStateFlow作为状态流，在GameScreen中collet为状态State（collectAsState）并使用
**data**：数据层 \\
- GuessItem：存储的单词Entity

## init: 2023/11/21
实现了使用GameScreen简单界面，和ViewModel管理游戏状态。
定义GuessItem单词数据库
