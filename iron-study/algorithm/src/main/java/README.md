二叉树

没个节点有零个或多个子节点
没有父节点的节点为根节点
每一个非根节点只有一个父节点
每个节点及其后代节点整体上可以看做一棵树，称为当前节点的父节点的一个子树

节点的度:一个节点含有的子树的个数称为该节点的度
叶节点:度为0的节点称为叶节点。也可以叫做终端节点
分支结点:度不为0的节点
结点的层次:从根结点开始，根节点的层次为1，根的直接后继层次为2 依此类推



堆
他是完全二叉树,除了树的最后一层结点不需要是满的，其他的每一层从左到右都是满的，如果最后一层结点不是满的 那么要求左满右不满
他通常用数组来实现

- 如果一个几点的位置是k，那么他的父亲结点的位置为（k/2）而他的2个子节点的位置分别是2k和2k+1，这压根在不适用指针的情况下，我们也可以通过计算数组的索引在数种上下移动
从a[k]向上一层，九令k等于k/2 向下一层九令k等于2k或者2k+1

每个结点都大于等于它的2个子节点，这里要注意堆中仅仅规定了每个结点大于等于他的2个子节点，但是这个2个子结点的顺序并没有做规定，跟我们之前学习的二叉树是有区别的




优先队列: 
队列中优先级高的先执行
最大优先对列
最小优先队列


