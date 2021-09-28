import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import Login from "./pages/Login";
import Browse from "./pages/Browse";
import AuthProvider from "./auth/AuthProvider";
import DocumentDetails from "./pages/DocumentDetail";
import Upload from "./pages/Upload";
import Main from "./pages/Main";
import DocumentEdit from "./pages/DocumentEdit"
import Filter from "./pages/Filter";


export default function App() {
    return (
        <AuthProvider>
            <Router>
                <Switch>
                    <Route exact path="/" component={Main} />
                    <Route path="/login" component = {Login} />
                    <Route path="/browse" component={Browse} />
                    <Route path="/upload" component={Upload} />
                    <Route path="/filter" component={Filter} />
                    <Route path="/details/:documentId" component={DocumentDetails} />
                    <Route path="/edit/:documentId" component={DocumentEdit} />
                </Switch>
            </Router>
        </AuthProvider>
);
}
