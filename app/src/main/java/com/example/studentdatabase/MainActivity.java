package com.example.studentdatabase;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText etName, etCourse, etYear;
    private Button btnAdd, btnUpdate, btnDelete, btnClear;
    private ListView lvStudents;

    private List<Student> studentList;
    private ArrayAdapter<Student> adapter;
    private int selectedStudentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etCourse = findViewById(R.id.etCourse);
        etYear = findViewById(R.id.etYear);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnClear);
        lvStudents = findViewById(R.id.lvStudents);

        loadStudentList();

        lvStudents.setOnItemClickListener((parent, view, position, id) -> {
            Student student = studentList.get(position);
            selectedStudentId = student.getId();
            etName.setText(student.getName());
            etCourse.setText(student.getCourse());
            etYear.setText(String.valueOf(student.getYear()));
            Toast.makeText(this, "Selected: " + student.getName(), Toast.LENGTH_SHORT).show();
        });

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String course = etCourse.getText().toString().trim();
            String yearStr = etYear.getText().toString().trim();

            if (name.isEmpty() || course.isEmpty() || yearStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int year;
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid year number", Toast.LENGTH_SHORT).show();
                return;
            }

            long result = dbHelper.insertStudent(name, course, year);
            if (result != -1) {
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                clearForm();
                loadStudentList();
            } else {
                Toast.makeText(this, "Error adding student", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(v -> {
            if (selectedStudentId == -1) {
                Toast.makeText(this, "Please select a student from the list to update", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = etName.getText().toString().trim();
            String course = etCourse.getText().toString().trim();
            String yearStr = etYear.getText().toString().trim();

            if (name.isEmpty() || course.isEmpty() || yearStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int year;
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid year number", Toast.LENGTH_SHORT).show();
                return;
            }

            int rowsAffected = dbHelper.updateStudent(selectedStudentId, name, course, year);
            if (rowsAffected > 0) {
                Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                clearForm();
                loadStudentList();
            } else {
                Toast.makeText(this, "Error updating student", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (selectedStudentId == -1) {
                Toast.makeText(this, "Please select a student from the list to delete", Toast.LENGTH_SHORT).show();
                return;
            }

            int rowsDeleted = dbHelper.deleteStudent(selectedStudentId);
            if (rowsDeleted > 0) {
                Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                clearForm();
                loadStudentList();
            } else {
                Toast.makeText(this, "Error deleting student", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(v -> {
            clearForm();
            Toast.makeText(this, "Form cleared", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadStudentList() {
        studentList = dbHelper.getAllStudents();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        lvStudents.setAdapter(adapter);
    }

    private void clearForm() {
        etName.setText("");
        etCourse.setText("");
        etYear.setText("");
        selectedStudentId = -1;
    }
}
