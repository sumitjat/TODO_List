package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateTaskActivity extends AppCompatActivity {
    Task task;

    private EditText editTextTask , editTextDesc , editTextFinsishby;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinsishby = findViewById(R.id.editTextFinishBy);

        checkBox = findViewById(R.id.checkBoxFinished);

        task = (Task) getIntent().getSerializableExtra("task");

        loadTask(task);





    }

    private void loadTask(Task task)
    {
            editTextTask.setText(task.getTask());
            editTextDesc.setText(task.getDesc());
            editTextFinsishby.setText(task.getFinsishby());

            checkBox.setChecked(task.getFinished());


    }

    public void onupdate(View view)
    {
        updateTask(task);
    }

    public void ondelete(View view)
    {

        deleteTask(task);
    }

    // update and delete method in that particular task
    private void updateTask(final Task task) {
        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinsishby.getText().toString().trim();

        if (sTask.isEmpty()) {
            editTextTask.setError("Task required");
            editTextTask.requestFocus();
            return;
        }
        if (sDesc.isEmpty()) {
            editTextDesc.setError("Desc required");
            editTextDesc.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()) {
            editTextFinsishby.setError("Finish by required");
            editTextFinsishby.requestFocus();
            return;
        }

        class UpdateTask extends AsyncTask<Void,Void,Void>
        {

            @Override
            protected Void doInBackground(Void... voids) {

                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinsishby(sFinishBy);
                task.setFinished(checkBox.isChecked());

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().
                        taskDao().update(task);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
            }

        }

        UpdateTask task1=new UpdateTask();
        task1.execute();

    }

    private void deleteTask(final Task task)
    {
       class  DeleteTask extends AsyncTask<Void,Void,Void>
        {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));

            }
        }
        DeleteTask deleteTask = new DeleteTask();
       deleteTask.execute();
    }
}