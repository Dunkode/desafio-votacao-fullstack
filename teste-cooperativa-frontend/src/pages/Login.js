import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Login() {
    const [cpf, setCpf] = useState('');
    const navigate = useNavigate();

    const handleLogin = () => {
        // Aqui você pode adicionar a lógica de login com o CPF
        alert(`CPF digitado: ${cpf}`);
    };

    const handleNavigate = () => {
        navigate();
    };

    return (
        <div style={{ maxWidth: 300, margin: '100px auto', textAlign: 'center' }}>
            <h2>Login</h2>
            <input
                type="text"
                placeholder="Digite seu CPF"
                value={cpf}
                onChange={e => setCpf(e.target.value)}
                style={{ width: '100%', padding: 8, marginBottom: 16 }}
            />
            <br />
            <button onClick={handleLogin} style={{ width: '100%', padding: 8 }}>
                Logar
            </button>
            <br /><br />
            <span
                onClick={() => navigate('/associateForm')}
                style={{ color: 'blue', textDecoration: 'underline', cursor: 'pointer' }}
            >
                Realizar cadastro
            </span>
        </div>
    );
}

export default Login;