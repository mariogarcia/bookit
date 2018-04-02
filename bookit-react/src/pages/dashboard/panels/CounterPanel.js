import React from 'react'

export const CounterPanel = (props) => {
    const css = `fa fa-${props.icon} f-s-40 color-primary`

    return (
        <div className="card p-30">
            <div className="media">
                <div className="media-left media media-middle">
                    <span><i className={css}></i></span>
                </div>
                <div className="media-body media-text-right">
                    <h2>{props.count}</h2>
                    <p className="m-b-0">{props.title} </p>
                </div>
            </div>
        </div>
    )
}
