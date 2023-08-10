

class ApiContext {

    static URL = "http://localhost:8080"

    static getUrl = () => this.URL;
    static setUrl = (url) => this.URL = url;

}


export default ApiContext;