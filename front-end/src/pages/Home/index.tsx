/* eslint-disable @typescript-eslint/no-unused-vars */
import React, { useEffect, useState, ChangeEvent } from 'react';
import { format } from 'date-fns';
import { ptBR } from 'date-fns/locale';

import api from '../../services/api'

import Checkbox from '@material-ui/core/Checkbox';
import { DeleteOutline } from '@material-ui/icons';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import './styles.css'


interface Item {
    id: number;
    nome: string;
    describ: string;
    feito: boolean;
}

const Home = () => {

    const date: string = format(new Date(), 'MMMM yyyy', { locale: ptBR });

    const [list, setLista] = useState<Item[]>([])
    const [data, setData] = useState({
        id: '',
        nome: '',
        describ: '',
        feito: false
    });

    async function handleChecked(id: number) {
        let newList = await list.map(iten =>
            iten.id === id
                ? { ...iten, feito: true }
                : iten
        )

        const item = newList.find(item => item.id === id)

        await api.put('tarefa', item)
        setLista(newList);
    }

    useEffect(() => {
        api.get('tarefas').then(res => {
            setLista(res.data);
        })
    }, []);

    function handleName(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target
        setData({ ...data, [name]: value })
    }

    function addTasks() {
        api.post('tarefa', data)
        window.location.reload();
    }

    async function deleteTask(id: number) {
        await api.delete(`tarefa/${id}`)
        window.location.reload();
    }

    return (
        <>
            <div className="content" >
                <header>
                    <h2>Lista de Tarefas {date}</h2>
                </header>
                <main className="lista">
                    <ul>
                        {list.map(item => (
                            <li key={item.id}>
                                <div className="items">
                                    <Checkbox
                                        checked={item.feito}
                                        onClick={() => handleChecked(item.id)}
                                        color="primary"
                                        inputProps={{ 'aria-label': 'secondary checkbox' }}
                                    />
                                    <h4 style={item.feito ? { textDecoration: 'line-through' } : {}} >{`${item.nome}`}</h4>
                                    <DeleteOutline className="icondelete" style={{ color: '#FF0000' }} onClick={() => deleteTask(item.id)} />
                                </div>
                                <hr />
                            </li>
                        ))}

                    </ul>

                </main>
                <div className="button" >
                    <TextField label="Digite uma nova tarefa:" onChange={handleName} type="text" name="nome" id="nome" />
                    <Button style={{ marginTop: 20 }} variant="contained" color="primary" type="submit" onClick={() => addTasks()} >Salvar</Button>
                </div>
            </div>
        </>
    )
}

export default Home;
