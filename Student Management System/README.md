# Student Management System

A simple web application to manage student records.

## Live Demo
- Frontend: https://studentsystem-five.vercel.app/
- Backend API: https://student-management-backend-exr8.onrender.com/

## Features
- Add, view, edit and delete students
- Store student details in MongoDB

## Tech Stack
- Frontend: React, Vite
- Backend: Node.js, Express
- Database: MongoDB

## Run Locally

### Backend
```bash
cd Backend
npm install
```

Create a `.env` file in the Backend folder:
```env
MONGO_URI=your_mongodb_connection_string
PORT=5000
```

Start the backend:
```bash
node server.js
```

### Frontend
Open another terminal:
```bash
cd Frontend
npm install
npm run dev
```

For deployment, set `VITE_API_URL` to your backend URL in the frontend environment variables.
