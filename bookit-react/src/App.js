import React, { Component } from 'react';

import { LinkListPage } from './pages/links/LinkListPage'
import BookListPage from './pages/books/BookListPage'
import LoginPage from './pages/login/LoginPage'
import { Switch, Route } from 'react-router-dom'

class App extends Component {
  render() {
      return (
          <Switch>
              <Route exact path='/books' component={BookListPage}/>
              <Route path='/links' component={LinkListPage}/>
              <Route path='/login' component={LoginPage} />
          </Switch>
      );
  }
}

export default App;
