const API_AUTH = "http://localhost:8080/api/auth/me"

document.addEventListener("DOMContentLoaded", cargarSesion);

asyn function cargarUsuario() {
    const menu = document.getElementById("menu-usuario");
    if (!menu) {
        return;
    }

    try {
        const token = localStorage.getItem('token');

        const respuesta = await fetch(API_AUTH, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Authorization': 'Basic' + token
            }
        });

        if (respuesta.ok) {
            const usuario = await respuesta.json();
            console.log('Respuesta OK:', usuario);
            menu.innerHTML = `
                <a href="usuario-detalle.html">Mi Perfil</a>
                <ul class="submenu">
                        <li><a href="usuario-detalle">Detalles usuario</a></li>
                        <li><a href="index.html" id="logout">Cerrar sesión</a></li>
                </ul>
            `;

            document.getElementById("logout").addEventListener("click", cerrarSesion);
        } else {
            localStorage.removeItem('token');

            menu.innerHTML = '<a href="login.html">Iniciar sesión</a>';
        }
    } catch(error) {
        console.error('Error inesperado: ', error);
        menu.innerHTML = '<a href="login.html">Iniciar sesión</a>';
        throw error;
    }
}

function cerrarSesion(event) {
    event.preventDefault();
    localStorage.removeItem('token');
    window.location.href = "login.html";
}

