import React from "react"

class ListOfAlbums extends React.Component {
    constructor(props) {
        super(props)
        this.state = {data: []}
    }

    async componentDidMount() {
        let json = await fetch("/api/albums").then(r => r.json())
        this.setState({data: json})
    }

    render() {
        return (
            <ul>
                {this.state.data.map(item =>
                    <li>
                        {item.title}
                        
                    </li>)}
            </ul>
        )
    }
}

export default ListOfAlbums
