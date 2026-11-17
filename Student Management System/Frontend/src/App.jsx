
import { useEffect, useState } from "react";
import "./App.css";

function App() {

    const [students, setStudents] = useState([]);

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [rollNumber, setRollNumber] = useState("");
    const [department, setDepartment] = useState("");

    const [editId, setEditId] = useState(null);

    function getStudents() {
        fetch("http://localhost:5000/api/students")
            .then(response => response.json())
            .then(data => setStudents(data))
            .catch(error => console.log(error));
    }

    useEffect(() => {
        getStudents();
    }, []);

    function saveStudent(e) {
        e.preventDefault();

        const student = {
            name,
            email,
            rollNumber,
            department
        };

        let url = "http://localhost:5000/api/students";
        let method = "POST";

        if (editId) {
            url = url + "/" + editId;
            method = "PUT";
        }

        fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(student)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Operation failed");
            }

            alert(editId ? "Student Updated" : "Student Added");

            clearForm();
            getStudents();
        })
        .catch(error => alert(error.message));
    }

    function editStudent(student) {
        setEditId(student._id);
        setName(student.name);
        setEmail(student.email);
        setRollNumber(student.rollNumber);
        setDepartment(student.department);
    }

    function deleteStudent(id) {

        if (!window.confirm("Are you sure you want to delete this student?")) {
            return;
        }

        fetch("http://localhost:5000/api/students/" + id, {
            method: "DELETE"
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Delete failed");
            }

            alert("Student Deleted");
            getStudents();
        })
        .catch(error => alert(error.message));
    }

    function clearForm() {
        setName("");
        setEmail("");
        setRollNumber("");
        setDepartment("");
        setEditId(null);
    }

    return (
        <div className="container">

            <h1>Student Management System</h1>

            <h2>{editId ? "Edit Student" : "Add Student"}</h2>

            <form onSubmit={saveStudent}>

                <input
                    type="text"
                    placeholder="Enter Name"
                    value={name}
                    onChange={e => setName(e.target.value)}
                    required
                />

                <input
                    type="email"
                    placeholder="Enter Email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                    required
                />

                <input
                    type="text"
                    placeholder="Enter Roll Number"
                    value={rollNumber}
                    onChange={e => setRollNumber(e.target.value)}
                    required
                />

                <input
                    type="text"
                    placeholder="Enter Department"
                    value={department}
                    onChange={e => setDepartment(e.target.value)}
                    required
                />

                <button type="submit">
                    {editId ? "Update Student" : "Add Student"}
                </button>

                {editId && (
                    <button type="button" onClick={clearForm}>
                        Cancel
                    </button>
                )}

            </form>

            <h2>Student Records</h2>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Roll Number</th>
                        <th>Department</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    {students.map(student => (
                        <tr key={student._id}>
                            <td>{student._id}</td>
                            <td>{student.name}</td>
                            <td>{student.email}</td>
                            <td>{student.rollNumber}</td>
                            <td>{student.department}</td>

                            <td>
                                <button onClick={() => editStudent(student)}>
                                    Edit
                                </button>

                                <button onClick={() => deleteStudent(student._id)}>
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <h3>Total Students: {students.length}</h3>

        </div>
    );
}

export default App;
