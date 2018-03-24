import React from 'react'
import { Link } from 'react-router-dom'

import './Card.css'

const createReductor = size => text => {
    return text && `${text.substring(0, size)}...`
}

const reduce = createReductor(100)

export const Card = (props) => {
    return (
        <div className="card">
            <img className="card-img-top" src={props.image} alt={props.title}/>
            <div className="card-block">
                <h4 className="card-title">{props.title}</h4>
                <p className="card-text">{reduce(props.text)}</p>
                <div className="card-actions">
                    <Link to={props.detailLink}><i data-fa-transform="grow-6" className="fa fa-eye"></i></Link>
                    <Link to={props.deleteLink}><i data-fa-transform="grow-6" className="fa fa-trash"></i></Link>
                </div>
            </div>
        </div>
    )
}
