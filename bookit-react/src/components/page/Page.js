import React from 'react'
import { Breadcrumbs } from '../../layouts/Breadcrumbs'
import { Footer } from '../../layouts/Footer'

const Page = (props) => (
    <div className="page-wrapper" style={{height:1200}}>
        <Breadcrumbs title={props.title}/>
        <div className="container-fluid">
            {props.children}
        </div>
        <Footer />
    </div>
)

export default Page
