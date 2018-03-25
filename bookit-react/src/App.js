import React, { Component } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom'
import { LocalStorage } from './client/storage/LocalStorage'
import LoginPage from './pages/login/LoginPage'
import DashboardPage from './pages/dashboard/DashboardPage'
import LanguageListPage from './pages/languages/LanguageListPage'
import BookListPage from './pages/books/BookListPage'
import AuthorListPage from './pages/authors/AuthorListPage'
import BartoloListPage from './pages/bartolos/BartoloListPage'
import Auth from './components/security'

class App extends Component {

  render() {
      const storage = new LocalStorage()
      const login = storage.get('login')
      const token = login ? login.token : login

      const Secured = Auth(LoginPage)(token)

      return (
          <Switch>
              <Route exact path='/' render={Secured(DashboardPage)} />
              <Route path='/books' render={Secured(BookListPage)} />
              <Route path='/bartolos' component={Secured(BartoloListPage)} />
              <Route path='/authors' render={Secured(AuthorListPage)} />
              <Route path='/languages' render={Secured(LanguageListPage)} />
              <Route path='/login' component={LoginPage} />
          </Switch>
      );
  }
}

export default App;
