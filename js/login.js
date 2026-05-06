API_LOGIN = "http://localhost:8080/api/auth/me";

const form = document.getElementById("form-login");
const servicio = document.getElementById("servicio");

form.addEventListener("submit", function(event) {
    event.preventDefault();
    iniciarSesion(event);
});

async function iniciarSesion(event) {
    const formData = new FormData(form);

    const email = formData.get("email");
    const contraseña = formData.get("contraseña");

    const credenciales = email + ":" + contraseña;

    const tokenEnviar = btoa(credenciales); 

    try {
        const respuesta = await fetch(API_LOGIN, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Basic' + tokenEnviar
            }
        });

        if (respuesta.ok)  {
            console.log("Inicio de sesión exitoso");
            localStorage.setItem('token', tokenEnviar);
            window.location.href = "index.html";
        } else {
            servicio.innerHTML += '<p class="aviso-form">Email o contraseña incorrectos</p>';
            console.log("Credenciales incorrectos");
        }
    } catch (error) {
        servicio.innerHTML += '<p class="aviso-form">No se pudo iniciar sesión por un error inesperado.</p>';
        console.error('Error inesperado: ', error);
    }
}

const inputPassword = document.getElementById("input-contraseña");
const botonVer = document.getElementById("ver-contraseña");

if (inputPassword && botonVer) {
    botonVer.addEventListener("click", (event) => {
        event.preventDefault();
    });

    botonVer.addEventListener("mousedown", () => {
        inputPassword.type = "text";
    });

    botonVer.addEventListener("mouseup", () => {
        inputPassword.type = "password";
    });

    //Si el ratón se va fuera del área del botón sin soltarlo
    botonVer.addEventListener("mouseleave", () => {
        inputPassword.type = "password";
    });
}

