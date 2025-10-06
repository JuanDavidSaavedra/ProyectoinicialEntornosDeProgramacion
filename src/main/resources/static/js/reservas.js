// Verificar si estamos en una página que necesita este script
if (document.getElementById('tablaReservas') || window.location.pathname.includes('form_reservas.html')) {

    document.addEventListener('DOMContentLoaded', function() {
        if (!SessionHelper.isLoggedIn()) {
            window.location.href = 'index.html';
            return;
        }

        if (document.getElementById('tablaReservas')) {
            const isAdmin = SessionHelper.isAdmin();

            const adminActions = document.getElementById('adminActions');
            const actionsHeader = document.getElementById('actionsHeader');
            if (!isAdmin) {
                if (adminActions) adminActions.style.display = 'none';
                if (actionsHeader) actionsHeader.style.display = 'none';
            } else {
                if (adminActions) adminActions.style.display = 'block';
                if (actionsHeader) actionsHeader.style.display = 'table-cell';
            }

            cargarReservas();
        }

        const reservaForm = document.getElementById('reservaForm');
        if (reservaForm) {
            const urlParams = new URLSearchParams(window.location.search);
            const reservaId = urlParams.get('id');

            if (reservaId) {
                const formTitle = document.getElementById('formTitle');
                if (formTitle) formTitle.textContent = '📅 Editar Reserva';
                cargarReserva(reservaId);
            }

            reservaForm.addEventListener('submit', guardarReserva);
        }
    });
}

async function cargarReservas() {
    try {
        const response = await RequestHelper.get('/reservas');

        if (!response || !response.data) {
            console.error('Respuesta inválida:', response);
            return;
        }

        const reservas = response.data;
        const tbody = document.getElementById('tablaReservas');

        if (!tbody) {
            console.error('No se encontró el elemento tablaReservas');
            return;
        }

        tbody.innerHTML = '';

        const currentUser = SessionHelper.getUser();
        const isAdmin = SessionHelper.isAdmin();

        // Filtrar reservas si no es admin
        const reservasFiltradas = isAdmin ? reservas :
            reservas.filter(r => r.usuario.id === currentUser.id);

        reservasFiltradas.forEach(reserva => {
            const tr = document.createElement('tr');

            let accionesHTML = '';
            if (isAdmin || reserva.usuario.id === currentUser.id) {
                accionesHTML = `
                    <a href="form_reservas.html?id=${reserva.id}" class="btn btn-sm btn-warning">Editar</a>
                    <button onclick="eliminarReserva(${reserva.id})" class="btn btn-sm btn-danger">Eliminar</button>
                `;
            } else {
                accionesHTML = '<span class="text-muted">Solo lectura</span>';
            }

            tr.innerHTML = `
                <td>${reserva.id}</td>
                <td>${reserva.usuario.nombre}</td>
                <td>${reserva.cancha.nombre} (${reserva.cancha.deporte})</td>
                <td>${new Date(reserva.fecha).toLocaleDateString()}</td>
                <td>${reserva.horaInicio}</td>
                <td>${reserva.horaFin}</td>
                <td><span class="badge ${reserva.estado === 'ACTIVA' ? 'bg-success' : 'bg-secondary'}">${reserva.estado}</span></td>
                <td>${accionesHTML}</td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error('Error al cargar reservas:', error);
        alert('Error al cargar reservas: ' + error.message);
    }
}

async function eliminarReserva(id) {
    if (confirm('¿Está seguro de eliminar esta reserva?')) {
        try {
            await RequestHelper.delete(`/reservas/${id}`);
            cargarReservas();
        } catch (error) {
            alert('Error al eliminar reserva: ' + error.message);
        }
    }
}

async function cargarUsuariosYCanchas() {
    try {
        // Cargar usuarios
        const usuariosResponse = await RequestHelper.get('/usuarios');
        const usuariosSelect = document.getElementById('usuarioId');

        if (usuariosSelect) {
            usuariosResponse.data.forEach(usuario => {
                const option = document.createElement('option');
                option.value = usuario.id;
                option.textContent = `${usuario.nombre} (${usuario.usuario})`;
                usuariosSelect.appendChild(option);
            });
        }

        // Cargar canchas
        const canchasResponse = await RequestHelper.get('/canchas');
        const canchasSelect = document.getElementById('canchaId');

        if (canchasSelect) {
            canchasResponse.data.forEach(cancha => {
                const option = document.createElement('option');
                option.value = cancha.id;
                option.textContent = `${cancha.nombre} - ${cancha.deporte} ($${cancha.precioHora.toLocaleString()}/hora)`;
                canchasSelect.appendChild(option);
            });
        }
    } catch (error) {
        alert('Error al cargar datos: ' + error.message);
    }
}

async function cargarReserva(id) {
    try {
        const response = await RequestHelper.get(`/reservas/${id}`);
        const reserva = response.data;

        document.getElementById('reservaId').value = reserva.id;
        document.getElementById('usuarioId').value = reserva.usuario.id;
        document.getElementById('canchaId').value = reserva.cancha.id;
        document.getElementById('fecha').value = reserva.fecha;
        document.getElementById('horaInicio').value = reserva.horaInicio;
        document.getElementById('horaFin').value = reserva.horaFin;
        document.getElementById('estado').value = reserva.estado;
    } catch (error) {
        alert('Error al cargar reserva: ' + error.message);
    }
}

async function guardarReserva(e) {
    e.preventDefault();

    const reservaData = {
        usuarioId: parseInt(document.getElementById('usuarioId').value),
        canchaId: parseInt(document.getElementById('canchaId').value),
        fecha: document.getElementById('fecha').value,
        horaInicio: document.getElementById('horaInicio').value,
        horaFin: document.getElementById('horaFin').value
    };

    const reservaId = document.getElementById('reservaId').value;

    try {
        if (reservaId) {
            // Para editar, usamos el endpoint de actualización de estado
            await RequestHelper.put(`/reservas/${reservaId}/estado`, document.getElementById('estado').value);
            alert('Reserva actualizada correctamente');
        } else {
            await RequestHelper.post('/reservas', reservaData);
            alert('Reserva creada correctamente');
        }

        window.location.href = 'reservas.html';
    } catch (error) {
        alert('Error al guardar reserva: ' + error.message);
    }
}

// Solo cargar usuarios y canchas si estamos en el formulario de reservas
if (document.getElementById('reservaForm')) {
    document.addEventListener('DOMContentLoaded', cargarUsuariosYCanchas);
}