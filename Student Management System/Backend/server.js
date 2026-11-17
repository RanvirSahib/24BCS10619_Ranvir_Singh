
const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
require("dotenv").config();

const app = express();

app.use(cors());
app.use(express.json());

const studentRoutes = require("./routes/studentRoutes");

app.use("/api/students", studentRoutes);


app.get("/", (req, res) => {
    res.send("Student Management Backend Running");
});

mongoose.connect(process.env.MONGO_URI)
    .then(() => {
        console.log("MongoDB Connected Successfully");

        app.listen(process.env.PORT, () => {
            console.log("Server running on port " + process.env.PORT);
        });
    })
    .catch((error) => {
        console.log("MongoDB Connection Error:", error);
    });
