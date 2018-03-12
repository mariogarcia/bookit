import React from 'react'

export const Breadcrumbs = (props) => (
    <div className="row page-titles">
        <div className="col-md-5 align-self-center">
            <h3 className="text-primary">{props.title}</h3> </div>
        <div className="col-md-7 align-self-center">
            <ol className="breadcrumb">
                <li className="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                <li className="breadcrumb-item active">{props.title}</li>
            </ol>
        </div>
    </div>
)
