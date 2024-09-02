package com.breezevasista.features.mylearning
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.breezevasista.R
import com.breezevasista.app.NetworkConstant
import com.breezevasista.app.Pref
import com.breezevasista.app.types.FragType
import com.breezevasista.app.utils.AppUtils
import com.breezevasista.base.presentation.BaseActivity
import com.breezevasista.base.presentation.BaseFragment
import com.breezevasista.features.dashboard.presentation.DashboardActivity
import com.breezevasista.features.mylearning.apiCall.LMSRepoProvider
import com.pnikosis.materialishprogress.ProgressWheel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.performance_item.tv_topic_name
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber


class SearchLmsLearningFrag : BaseFragment() , View.OnClickListener, MyLearningProgressAdapter.OnItemClickListener {

    private lateinit var mContext: Context
    lateinit var progress_wheel: ProgressWheel

    private lateinit var ll_lms_performance: LinearLayout
    private lateinit var iv_lms_performance: ImageView
    private lateinit var tv_lms_performance: TextView

    private lateinit var ll_lms_mylearning: LinearLayout
    private lateinit var iv_lms_mylearning: ImageView
    private lateinit var tv_lms_mylearning: TextView

    private lateinit var ll_lms_leaderboard: LinearLayout
    private lateinit var iv_lms_leaderboard: ImageView
    private lateinit var tv_lms_leaderboard: TextView

    private lateinit var ll_lms_knowledgehub: LinearLayout
    private lateinit var iv_lms_knowledgehub: ImageView
    private lateinit var tv_lms_knowledgehub: TextView

    lateinit var ll_voice: LinearLayout
    lateinit var et_search: EditText
    private var  suffixText:String = ""
    private lateinit var rv_mylearning_progress: RecyclerView
    private lateinit var ll_frag_search_root: LinearLayout
    private lateinit var ll_continue_learning: LinearLayout
    private lateinit var ll_no_data: LinearLayout
    private lateinit var final_dataL: ArrayList<LarningList>


    companion object {
        fun getInstance(objects: Any): SearchLmsLearningFrag {
            val fragment = SearchLmsLearningFrag()
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_search_lms_learning, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        //performance
        ll_lms_performance = view.findViewById(R.id.ll_lms_performance)
        iv_lms_performance = view.findViewById(R.id.iv_lms_performance)
        tv_lms_performance = view.findViewById(R.id.tv_lms_performance)

        //mylearning
        ll_lms_mylearning = view.findViewById(R.id.ll_lms_mylearning)
        iv_lms_mylearning = view.findViewById(R.id.iv_lms_mylearning)
        tv_lms_mylearning = view.findViewById(R.id.tv_lms_mylearning)

        //leaderboard
        ll_lms_leaderboard = view.findViewById(R.id.ll_lms_leaderboard)
        iv_lms_leaderboard = view.findViewById(R.id.iv_lms_leaderboard)
        tv_lms_leaderboard = view.findViewById(R.id.tv_lms_leaderboard)

        //knowledgehub
        ll_lms_knowledgehub = view.findViewById(R.id.ll_lms_knowledgehub)
        iv_lms_knowledgehub = view.findViewById(R.id.iv_lms_knowledgehub)
        tv_lms_knowledgehub = view.findViewById(R.id.tv_lms_knowledgehub)

        iv_lms_mylearning.setImageResource(R.drawable.my_learning_filled_clr)
        iv_lms_leaderboard.setImageResource(R.drawable.leaderboard_new)
        iv_lms_performance.setImageResource(R.drawable.my_performance_new)
        iv_lms_knowledgehub.setImageResource(R.drawable.knowledge_hub_new)
        iv_lms_performance.setColorFilter(ContextCompat.getColor(mContext, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY)
        iv_lms_leaderboard.setColorFilter(ContextCompat.getColor(mContext, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY)
        iv_lms_knowledgehub.setColorFilter(ContextCompat.getColor(mContext, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY)

        tv_lms_performance.setTextColor(getResources().getColor(R.color.black))
        tv_lms_mylearning.setTextColor(getResources().getColor(R.color.toolbar_lms))
        tv_lms_leaderboard.setTextColor(getResources().getColor(R.color.black))
        tv_lms_knowledgehub.setTextColor(getResources().getColor(R.color.black))

        rv_mylearning_progress = view.findViewById(R.id.rv_mylearning_progress)
        et_search = view.findViewById(R.id.et_frag_learning_search)
        ll_voice = view.findViewById(R.id.iv_frag_spk)
        ll_frag_search_root = view.findViewById(R.id.ll_frag_search_root)
        progress_wheel = view.findViewById(R.id.ll_frag_my_learning_progress_wheel)
        ll_no_data = view.findViewById(R.id.ll_no_data)
        ll_continue_learning = view.findViewById(R.id.ll_continue_learning)

        progress_wheel.stopSpinning()

        getMyLarningInfoAPI()

        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //setLearningData(final_dataL)
                var tempSearchL = final_dataL.filter { it.content_title.contains(et_search.text.toString().trim(), ignoreCase = true) || it.content_description.contains(et_search.text.toString().trim(), ignoreCase = true)  } as ArrayList<LarningList>
                    progress_wheel.stopSpinning()
                    if(tempSearchL.size>0){
                        rv_mylearning_progress.visibility =View.VISIBLE
                        ll_continue_learning.visibility =View.VISIBLE
                        ll_no_data.visibility =View.GONE
                        setLearningData(tempSearchL)
                    }else{
                        rv_mylearning_progress.visibility =View.GONE
                        ll_continue_learning.visibility =View.GONE
                        ll_no_data.visibility =View.VISIBLE
                    }
                }
            override fun afterTextChanged(s: Editable) {}
        })

        ll_lms_performance.setOnClickListener(this)
        ll_lms_mylearning.setOnClickListener(this)
        ll_lms_leaderboard.setOnClickListener(this)
        ll_lms_knowledgehub.setOnClickListener(this)
        ll_voice.setOnClickListener(this)
        ll_frag_search_root.setOnClickListener(this)

    }

    private fun getMyLarningInfoAPI() {
        try {
            progress_wheel.visibility = View.VISIBLE
            Timber.d("deleteImei call" + AppUtils.getCurrentDateTime())
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getMyLraningInfo(Pref.user_id!!)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as MyLarningListResponse
                        if (response.status == NetworkConstant.SUCCESS) {
                            progress_wheel.visibility = View.GONE
                            val data = response.learning_content_info_list
                            try {
                                final_dataL = ArrayList<LarningList>()
                                for (i in 0..response.learning_content_info_list.size - 1) {
                                    if (response.learning_content_info_list[i].content_url != null) {
                                        final_dataL.add(response.learning_content_info_list[i])
                                    }
                                }
                                setLearningData(final_dataL)
                            }catch (ex:Exception){
                                ex.printStackTrace()
                            }
                        }else{
                            progress_wheel.visibility = View.GONE
                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))
                        }
                    }, { error ->
                        progress_wheel.visibility = View.GONE
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        }
        catch (ex: Exception) {
            ex.printStackTrace()
            progress_wheel.visibility = View.GONE
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }
    }

    private fun setLearningData(finalDatal: ArrayList<LarningList>) {

        rv_mylearning_progress.layoutManager =
            LinearLayoutManager(mContext)
            val adapter = MyLearningProgressAdapter(mContext, finalDatal, this)
            rv_mylearning_progress.adapter = adapter
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            ll_lms_mylearning.id -> {
                (mContext as DashboardActivity).loadFragment(
                    FragType.SearchLmsLearningFrag,
                    true,
                    ""
                )
            }

            ll_lms_leaderboard.id -> {
                (mContext as DashboardActivity).loadFragment(
                    FragType.LeaderboardLmsFrag,
                    true,
                    ""
                )
            }

            ll_lms_knowledgehub.id -> {
                (mContext as DashboardActivity).loadFragment(
                    FragType.SearchLmsKnowledgeFrag,
                    true,
                    ""
                )
            }

            ll_lms_performance.id -> {
                (mContext as DashboardActivity).loadFragment(
                    FragType.MyPerformanceFrag,
                    true,
                    ""
                )
            }

            ll_frag_search_root.id -> {
                AppUtils.hideSoftKeyboard(mContext as DashboardActivity)
               // if (!et_search.text.toString().trim().equals("")) {
                    progress_wheel.spin()
                    doAsync {
                        var tempSearchL = final_dataL.filter { it.content_title.contains(et_search.text.toString().trim(), ignoreCase = true) || it.content_description.contains(et_search.text.toString().trim(), ignoreCase = true)  } as ArrayList<LarningList>
                        uiThread {
                            progress_wheel.stopSpinning()
                            if(tempSearchL.size>0){
                                rv_mylearning_progress.visibility =View.VISIBLE
                                ll_continue_learning.visibility =View.VISIBLE
                                ll_no_data.visibility =View.GONE
                                setLearningData(tempSearchL)
                            }else{
                                rv_mylearning_progress.visibility =View.GONE
                                ll_continue_learning.visibility =View.GONE
                                ll_no_data.visibility =View.VISIBLE
                            }
                        }
                    }
              //  }
            }
            ll_voice.id ->{
                suffixText = et_search.text.toString().trim()
                startVoiceInput()
            }
        }
    }

    fun updateList(){
        getMyLarningInfoAPI()
    }

    private fun startVoiceInput() {
        val intent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"en-US")
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US")
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"hi")
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.ENGLISH)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?")
        try {
            startActivityForResult(intent, 7009)
        } catch (a: ActivityNotFoundException) {
            a.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 7009){
            try{
                val result = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                var t= result!![0]
                if(suffixText.length>0 && !suffixText.equals("")){
                    var setFullText = suffixText+t
                    et_search.setText(suffixText+t)
                    et_search.setSelection(setFullText.length);
                }else{
                    var SuffixPostText = t+et_search.text.toString()
                    et_search.setText(SuffixPostText)
                    et_search.setSelection(SuffixPostText.length);
                }
            }
            catch (ex:Exception) {
                ex.printStackTrace()
            }
        }
    }

    override fun onItemClick(item: LarningList) {
        val store_topic_id = item.topic_id
        val store_topic_name = item.topic_name
        val store_content_id = item.content_id
        getwiselistAPIcalling(store_topic_id,store_content_id,store_topic_name)
        //(mContext as DashboardActivity).loadFragment(FragType.MyLearningVideoPlay, true, item)
    }

    private fun getwiselistAPIcalling(store_topic_id: Int, store_content_id: Int ,store_topic_name:String) {
        try {
            progress_wheel.visibility = View.VISIBLE
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getTopicsWiseVideo(Pref.user_id!!, store_topic_id.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as VideoTopicWiseResponse
                        if (response.status == NetworkConstant.SUCCESS) {
                            progress_wheel.visibility = View.GONE
                            try {
                                if (response.content_list!=null && response.content_list.size>0) {

                                val singleItemVideo = response.content_list.filter { it.content_id.toString().equals(store_content_id.toString()) }
                                    (mContext as DashboardActivity).loadFragment(FragType.MyLearningVideoPlay, true, singleItemVideo+"~"+store_topic_id+"~"+store_topic_name)

                                }else{
                                    Toast.makeText(mContext, "No video found", Toast.LENGTH_SHORT).show()

                                }
                            }catch (ex:Exception){
                                ex.printStackTrace()
                            }
                        }else{
                            progress_wheel.visibility = View.GONE

                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))

                        }
                    }, { error ->
                        progress_wheel.visibility = View.GONE

                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            progress_wheel.visibility = View.GONE

            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }

    }


}