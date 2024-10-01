package com.zzp.dtrip.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zzp.dtrip.R
import com.zzp.dtrip.adapter.StudyAdapter
import com.zzp.dtrip.data.StudyItem
import com.zzp.dtrip.databinding.FragmentStudyBinding

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class StudyFragment : Fragment() {

    private var _binding: FragmentStudyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStudyBinding.inflate(inflater, container, false)

        val view = binding.root

        // 初始化 RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.study_rv)

        // 设置布局管理器
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 创建数据列表
        val dataList = listOf(
            StudyItem(R.drawable.study_image1,"基础手语训练营", "为您提供全面的基础手语学习，包括手势表达、数字、字母、常用词汇以及简单句子的构建等。您将逐步学习手语的基本规则和技巧，并通过实践来提高您的手语交流能力。","共11讲"),
            StudyItem(R.drawable.study_image2,"进阶手语训练营", "大量的练习和角色扮演，以帮助您巩固所学知识，并在实际情境中应用手语进行沟通。我们的教练将提供个别指导和反馈，确保您能够得到充分的实践机会和个性化的辅导。","共12讲"),
            StudyItem(R.drawable.study_image3,"安全防范手册", "我们将介绍各种常见灾难和紧急情况，如火灾、地震、洪水、自然灾害等，并教授听障人士在这些情况下的应对策略和自救技巧。","共9讲"),
            StudyItem(R.drawable.study_image4,"日常社交礼仪课", "这个课程旨在帮助听障人士掌握日常社交场合中的礼仪技巧，提高他们的自信和互动能力，使他们能够更轻松地融入社交环境。","共10讲"),
            StudyItem(R.drawable.study_image2,"进阶手语训练营2", "大量的练习和角色扮演，以帮助您巩固所学知识，并在实际情境中应用手语进行沟通。我们的教练将提供个别指导和反馈，确保您能够得到充分的实践机会和个性化的辅导。","共12讲"),
            )

        // 创建适配器并设置给 RecyclerView
        val adapter = StudyAdapter(dataList)
        recyclerView.adapter = adapter

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}