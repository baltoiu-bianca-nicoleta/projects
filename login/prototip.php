<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PROTOTIP</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <style>
        body {
            background: #3399ff;
        }

        .circle {
            position: absolute;
            border-radius: 50%;
            background: white;
            animation: ripple 15s infinite;
            box-shadow: 0px 0px 1px 0px #508fb9;
        }

        .small {
            width: 200px;
            height: 200px;
            left: -100px;
            bottom: -100px;
        }

        .medium {
            width: 400px;
            height: 400px;
            left: -200px;
            bottom: -200px;
        }

        .large {
            width: 600px;
            height: 600px;
            left: -300px;
            bottom: -300px;
        }

        .xlarge {
            width: 800px;
            height: 800px;
            left: -400px;
            bottom: -400px;
        }

        .xxlarge {
            width: 1000px;
            height: 1000px;
            left: -500px;
            bottom: -500px;
        }

        .shade1 {
            opacity: 0.2;
        }

        .shade2 {
            opacity: 0.5;
        }

        .shade3 {
            opacity: 0.7;
        }

        .shade4 {
            opacity: 0.8;
        }

        .shade5 {
            opacity: 0.9;
        }

        @keyframes ripple {
            0% {
                transform: scale(0.8);
            }

            50% {
                transform: scale(1.2);
            }

            100% {
                transform: scale(0.8);
            }
        }

        .custom-border {
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 10px;
        }

        h2 {
            color: white;
            width: 20 cm;
            height: 15 cm;
            position: center;
            padding: 50px;
            text-align: center;
        }

        label {
            color: white;
            width: 10 cm;
            height: 5 cm;
        }

        .container-fluid {
            max-width: 700px;
        }

        input[type=password],
        input[type=email] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            margin-top: 6px;
            margin-bottom: 16px;
            resize: vertical;
        }

        header {
            padding-left: 20%;
            position: left;
            color: white;
            font-size: 25px;
        }

        a {
            color: white;
        }
.input-group {
    position: relative;
    width: 100%;
}

.input-group .password-toggle-icon {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    cursor: pointer;
    z-index: 2;
}



    </style>
</head>
<body>
    <div class="ripple-background">
        <div class="circle xxlarge shade1"></div>
        <div class="circle xlarge shade2"></div>
        <div class="circle large shade3"></div>
        <div class="circle medium shade4"></div>
        <div class="circle small shade5"></div>
    </div>
    <header>FERMA BOD</header>
    <header>S.C L&D LUCA IND</header>
    <header>RO 6652160</header>

    <div class="container mt-3">
        <div class="custom-border">
            <h2 class="mb-4">LOGIN</h2>
    
            <form id="joinUsForm">
           
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>
                <div class="form-group password-group">
                    <label for="pwd">Password:</label>
                    <div class="input-group">
                        <input type="password" id="pwd" name="pwd" class="form-control" required>
                        <span class="password-toggle-icon"><i class="fa fa-eye"></i></span>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                <input type="checkbox" id="remember-me">
                <label for="remember-me">Remember me</label>
                <a href="#">Forgot Password?</a>
              
            </form>
    
        </div>
    </div>
    <script>
    document.getElementById('joinUsForm').addEventListener('submit', function(event) {
        event.preventDefault();
    
      
        const email = document.getElementById('email').value;
        const password = document.getElementById('pwd').value;
    
        // Simulăm o cerere către backend pentru autentificare
        authenticateUser(email, password);
    });
    
    // Funcția pentru autentificarea utilizatorului (de simulat cu backend-ul real)
    function authenticateUser(email, password) {
        // Aici trebuie să adaugi logica pentru cererea către backend
    
        // Verificare simplificată pentru demo (poți înlocui cu logica ta reală de backend)
        if (email === 'utilizator@exemplu.com' && password === 'parolasigura') {
            alert('Login successful!');
            window.location.href = 'home.html';  // Redirect către pagina home.html după autentificare
        } else {
            alert('Invalid email or password. Please try again.');
            document.getElementById('joinUsForm').reset();
        }
    }
    
    // Script pentru gestionarea ochiului care arată/ascunde parola
    const togglePassword = document.querySelector('.password-toggle-icon');
    const passwordField = document.getElementById('pwd');
    
    togglePassword.addEventListener('click', function() {
        const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordField.setAttribute('type', type);
        this.querySelector('i').classList.toggle('fa-eye-slash');
        this.querySelector('i').classList.toggle('fa-eye');
    });
    </script>
</body>
</html>
