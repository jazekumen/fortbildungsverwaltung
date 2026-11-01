package com.example.app3;

public class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    @Override public void onCreate(android.os.Bundle state) {
        super.onCreate(state); setContentView(R.layout.activity_main);
        android.view.View root=findViewById(android.R.id.content);
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(root,(view,insets)->{
            androidx.core.graphics.Insets bars=insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars());
            view.setPadding(bars.left,bars.top,bars.right,bars.bottom); return insets;
        });
    }
}
