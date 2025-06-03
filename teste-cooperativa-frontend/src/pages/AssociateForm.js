import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import associateService from '../api/associateService';

const AssociateForm = () => {
    const [name, setName] = useState('');
    const [document, setDocument] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        await associateService.createAssociate({ 'name' : name, 'document' : document })
            .then(response => {
                alert(response.data.message);
                navigate('/menu');
            })
            .catch(error => {
                console.log('Erro retornado da API:', error.response?.data);
                alert('Erro ao tentar cadastrar associado: ' + (error.response?.data?.message || 'Erro desconhecido'));
            });
        setName('');
        setDocument('');
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="associate-name">Name:</label>
                <input
                    id="associate-name"
                    type="text"
                    value={name}
                    onChange={e => setName(e.target.value)}
                    required
                />
            </div>
            <div>
                <label htmlFor="associate-cpf">CPF:</label>
                <input
                    id="associate-cpf"
                    type="text"
                    value={document}
                    onChange={e => setDocument(e.target.value)}
                    required
                />
            </div>
            <button type="submit">Cadastrar Associado</button>
        </form>
    );
};

export default AssociateForm;