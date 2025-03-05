


<?php
// Conectare la baza de date
$conn = new mysqli("localhost", "root", "", "baza_de_date");

// Verificare conexiune
if ($conn->connect_error) {
    die("Conexiunea a eșuat: " . $conn->connect_error);
}

// Preluare date din formular
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST['email'];
    $password = $_POST['pwd'];

    // Prevenire SQL Injection
    $email = $conn->real_escape_string($email);
    $password = $conn->real_escape_string($password);

    // Verificare în baza de date
    $sql = "SELECT * FROM logindetails WHERE email='$email'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        if ($password == $row['password']) {
            header('Location: home.html');
            exit();
        } else {
            echo "<script>alert('Login unsuccessful');</script>";
        }
    } else {
        echo "<script>alert('Invalid email or password. Please try again.');</script>";
    }
}

$conn->close();
?>
