import React, { Component } from 'react';
import { LinkListPage } from './pages/links/LinkListPage'
import BookListPage from './pages/books/BookListPage'
import LoginPage from './pages/login/LoginPage'
import Auth from './components/security'
import { Switch, Route, Redirect } from 'react-router-dom'
import { LocalStorage } from './client/storage/LocalStorage'

class App extends Component {

  render() {
      const storage = new LocalStorage()
      const login = storage.get('login')
      const token = login ? login.token : login

      const Secured = Auth(LoginPage)(token)

      return (
          <Switch>
              <Route exact path='/books' render={Secured(BookListPage)} />
              <Route path='/links' component={Secured(LinkListPage)} />
              <Route path='/login' component={LoginPage} />
          </Switch>
      );
  }
}

export default App;
