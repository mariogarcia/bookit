import React from 'react'

import { Card } from '../../components/card/Card'

/**
 * Loads the list of books
 */
export class BookList extends React.Component {
    componentDidMount () {
        this.props.listBooks()
    }

    render () {
        const detailLink = `/books/detail/1`
        const deleteLink = `/books/delete/1`
        const books = this.props.books.map((book, index) => (
                <Card
                    key={index}
                    image={book.get('imageUri')}
                    title={book.get('title')}
                    text={book.get('shortDescription')}
                    detailLink={detailLink}
                    deleteLink={deleteLink}
                    buttonText='Details' />
        ))

        return (
            <div className='card-body'>
                { books }
            </div>
        )
    }
}
