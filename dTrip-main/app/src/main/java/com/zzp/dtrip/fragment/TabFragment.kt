package com.zzp.dtrip.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zzp.dtrip.R
import com.zzp.dtrip.adapter.ForumPostAdapter
import com.zzp.dtrip.adapter.TabAdapter
import com.zzp.dtrip.data.ForumItemData

class TabFragment : Fragment() {

    companion object {
        private const val ARG_TAB_INDEX = "tab_index"

        fun newInstance(tabIndex: Int): TabFragment {
            val args = Bundle()
            args.putInt(ARG_TAB_INDEX, tabIndex)
            val fragment = TabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val tabIndex: Int by lazy {
        arguments?.getInt(ARG_TAB_INDEX) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ForumPostAdapter(getDataForTab(tabIndex))
        recyclerView.adapter = adapter
    }

    private fun getDataForTab(tabIndex: Int): List<ForumItemData> {
        // Dummy data for demonstration
        return when (tabIndex) {
            0 -> listOf(
                ForumItemData("有没有uu知道什么盲人好物吗？给妈妈买的", "如题，集美们有推荐的吗？", "沐上春风Clis", "2024-04-29","24","6","1"),
                ForumItemData("集美们手语课都看的哪个", "最近看看的手语课老师断更了想换换有没有什么好的推荐？", "不挂科不挂科", "2024-04-29","24","6","1"),
                ForumItemData("地图显示周边美食有点多，哪些好吃？", "如题，第一次来长沙", "小宝贝来啦", "2024-04-29","43","9","2"),
                ForumItemData("有点郁闷，感觉自己什么都不会", "最近家里出了一点事情，感觉自己什么忙都帮不上。。。", "草莓蔓蔓咖", "2024-04-29","3","5","0"),
                ForumItemData("人生，易如反掌", "最近抖子上全是这个，人生真的有那么简单吗？", "从不在最后放弃", "2024-04-29","39","12","3"),
                ForumItemData("五一假期家人们想去哪里玩", "翻遍了攻略也没找到心仪的地方，准备云游了", "你的彩虹雨", "2024-04-29","53","21","6"),
                ForumItemData("有没有手语学习搭子", "一个人学习好无聊好难学，想要一个努力的小搭子", "学习使我快乐", "2024-04-29","17","8","2"),
                ForumItemData("有人处对象吗", "渴望一个甜甜腻腻的青涩爱情，私我晒照，可爱小女生一枚~", "勿爱悟悔", "2024-04-29","11","5","3"),
                ForumItemData("有喜欢拍照的友友吗", "将来想做摄影师，希望能在这里找到志同道合的小伙伴", "看见我叫我去摸鱼", "2024-04-29","12","6","0"),
                ForumItemData("有会画画的家人吗", "素描的画应该先排线还是先临摹呀？有好一点的建议吗？", "天空蓝", "2024-04-29","10","2","1"),
                )
            1 -> listOf(
                ForumItemData("集美们手语课都看的哪个", "最近看看的手语课老师断更了想换换有没有什么好的推荐？", "不挂科不挂科", "2024-04-29","24","6","1"),
                ForumItemData("地图显示周边美食有点多，哪些好吃？", "如题，第一次来长沙", "小宝贝来啦", "2024-04-29","43","9","2"),
                ForumItemData("有点郁闷，感觉自己什么都不会", "最近家里出了一点事情，感觉自己什么忙都帮不上。。。", "草莓蔓蔓咖", "2024-04-29","3","5","0"),
                ForumItemData("人生，易如反掌", "最近抖子上全是这个，人生真的有那么简单吗？", "从不在最后放弃", "2024-04-29","39","12","3"),
                ForumItemData("五一假期家人们想去哪里玩", "翻遍了攻略也没找到心仪的地方，准备云游了", "你的彩虹雨", "2024-04-29","53","21","6"),
                )
            2 -> listOf(
                ForumItemData("有没有手语学习搭子", "一个人学习好无聊好难学，想要一个努力的小搭子", "学习使我快乐", "2024-04-29","17","8","2"),
                ForumItemData("有人处对象吗", "渴望一个甜甜腻腻的青涩爱情，私我晒照，可爱小女生一枚~", "勿爱悟悔", "2024-04-29","11","5","3"),
                ForumItemData("有喜欢拍照的友友吗", "将来想做摄影师，希望能在这里找到志同道合的小伙伴", "看见我叫我去摸鱼", "2024-04-29","12","6","0"),
                ForumItemData("有会画画的家人吗", "素描的画应该先排线还是先临摹呀？有好一点的建议吗？", "天空蓝", "2024-04-29","10","2","1"),
            )
            3 -> listOf(
                ForumItemData("有没有uu知道什么盲人好物吗？给妈妈买的", "如题，集美们有推荐的吗？", "沐上春风Clis", "2024-04-29","24","6","1"),
                ForumItemData("有点郁闷，感觉自己什么都不会", "最近家里出了一点事情，感觉自己什么忙都帮不上。。。", "草莓蔓蔓咖", "2024-04-29","3","5","0"),
                ForumItemData("地图显示周边美食有点多，哪些好吃？", "如题，第一次来长沙", "小宝贝来啦", "2024-04-29","43","9","2"),
                ForumItemData("有没有手语学习搭子", "一个人学习好无聊好难学，想要一个努力的小搭子", "学习使我快乐", "2024-04-29","17","8","2"),
                ForumItemData("有喜欢拍照的友友吗", "将来想做摄影师，希望能在这里找到志同道合的小伙伴", "看见我叫我去摸鱼", "2024-04-29","12","6","0"),
                ForumItemData("有会画画的家人吗", "素描的画应该先排线还是先临摹呀？有好一点的建议吗？", "天空蓝", "2024-04-29","10","2","1"),
            )
            else -> emptyList()
        }
    }
}