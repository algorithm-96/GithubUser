package com.rinaldialdi.githubuser.ui.Detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rinaldialdi.githubuser.databinding.ActivityDetailUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"
    }

    private lateinit var binding : ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id       = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl= intent.getStringExtra(EXTRA_AVATAR)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)


        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        viewModel.setUserDetail(username!!)
        viewModel.getUserDetail().observe(this,{
            if (it != null){
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"

                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
            }

        })

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if (count != null){
                    if (count>0){
                        binding.toggleFav.isChecked = true
                        _isChecked = true
                    } else{
                        binding.toggleFav.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.toggleFav.setOnClickListener {
            _isChecked = ! _isChecked
            if (_isChecked){
                viewModel.addToFavorite(username, id, avatarUrl!!)
            } else{
                viewModel.removeFromFavorite(id)
            }
            binding.toggleFav.isChecked = _isChecked
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }
}