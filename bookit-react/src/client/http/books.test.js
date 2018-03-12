import MockAdapter from 'axios-mock-adapter'
import http from './books'
import { client } from './index'

const mock = new MockAdapter(client)
const logErrorFn = (err) => console.error('err', err)

describe('Books/HTTP', function () {
    afterEach(function () {
        mock.reset()
    })

    describe('listing books', () => {
        it('should get a list of books when success', () => {
            mock.onPost('')
                .reply(
                    200,
                    JSON.stringify({
                        errors: null,
                        data: {
                            books: [{title: 'The Book'}]
                        }
                    }))

            http(mock.axiosInstance)
                .list()
                .then(data => {
                    expect(data.size).toEqual(1)
                })
                .catch(logErrorFn)
        })

        it('should get a message when failure', () => {
            mock.onPost('')
                .reply(
                    200,
                    JSON.stringify({
                        errors: [{
                            errorType: "ValidationError"
                        }],
                        data: null
                    }))

            http(mock.axiosInstance)
                .list()
                .then(data => {
                    expect(data).toBeUndefined()
                })
        })

        it('should get a message when server fails', done => {
            mock.onPost('').reply(500, JSON.stringify({}))

            http(mock.axiosInstance)
                .list()
                .then(resp => {
                    expect(resp.get('errors').size).toEqual(1)
                    done()
                })
        })
    })

})
