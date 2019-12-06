import React, { useEffect, useState } from "react";
import apiService from '../../Services/apiService';
import { Link } from "react-router-dom";

export default function App(props) {

    const [state, setstate] = useState([])

    useEffect(() => {
        
        apiService
        .call("posts")
        .getAll()
        .then(data => setstate(data.data) )
        
    },[])


    return (
        
        <div className="container">
            
            <div className="row mb-2">
                {state.map((element)=>{
                    return(
                        <div className="col-md-6">
                        <div className="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                            <div className="col p-4 d-flex flex-column position-static">
                                <strong className="d-inline-block mb-2 text-success">Bebida</strong>
                                <h3 className="mb-0">{element.titulo}</h3>
                                <div className="mb-1 text-muted">{element.data}</div>
                                <p className="mb-auto">{element.conteudo}</p>
                                <Link to={"/detalhes/" + element.bebida.id} className="stretched-link">Ver mais</Link>
                            </div>
                            <div className="col-auto d-none d-lg-block">
                            <img width="200" height="250" src={element.bebida.linkImg} />
                            </div>
                        </div>
                    </div>)
                })}
            </div>
        </div>

    )
}
