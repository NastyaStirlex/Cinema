package com.nastirlex.cinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nastirlex.cinema.databinding.SignInBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: SignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInBinding.inflate(layoutInflater)
        setContentView(R.layout.sign_up)
    }
}
