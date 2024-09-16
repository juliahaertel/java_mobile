package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);// habilitar o layout Edge-to-Edge, que torna o aplicativo "full screen"

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }*/


        private RecyclerView recyclerView;
        private TaskAdapter taskAdapter;
        private List<String> taskList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            recyclerView = findViewById(R.id.recyclerView);
            FloatingActionButton addTaskButton = findViewById(R.id.addTaskButton);

            taskList = new ArrayList<>();
            taskAdapter = new TaskAdapter(taskList, position -> {
                taskList.remove(position);
                taskAdapter.notifyItemRemoved(position);
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(taskAdapter);

            // Adicionar nova tarefa
            addTaskButton.setOnClickListener(view -> showAddTaskDialog());
        }

        private void showAddTaskDialog() {
            // Criar um diálogo para adicionar uma nova tarefa
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Adicionar Tarefa");

            final EditText input = new EditText(this);
            input.setHint("Digite a tarefa");
            builder.setView(input);

            builder.setPositiveButton("Adicionar", (dialog, which) -> {
                String task = input.getText().toString();
                if (!task.isEmpty()) {
                    taskList.add(task);
                    taskAdapter.notifyItemInserted(taskList.size() - 1);
                }
            });
            builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

            builder.show();
        }


}


