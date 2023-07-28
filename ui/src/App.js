import logo from './logo.svg'
import './App.css'
import {useState} from "react";

function App() {
  const [allCardSet, setAllCardSet] = useState(
    Array.from(Array(8).keys())
      .map(l => l + 1)
      .map(_ => {
        return {
          "yellow": false,
          "blue": false,
          "red": false
        }
      })
  )

  const innerBoxOnClick = (cardRow, inner) => {
    const newRow = allCardSet[cardRow]
    newRow[inner] = true
    const start = allCardSet.slice(0,cardRow)
    const end = allCardSet.slice(cardRow+1,allCardSet.length+1)
    const newCardSet = start.concat([newRow]).concat(end)
    setAllCardSet(newCardSet)
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo"/>
      </header>
      <main className={"App-main"}>
        {
          allCardSet.map((cardSet, cardRow, c) =>
            <div className={"outer-box"}>
              {
                ["yellow", "blue", "red"].map((inner, cardColumn, f) =>
                  <div
                    className={`inner-box ${inner} ${cardSet[inner] === true ? "box-disabled" : null}`}
                    onClick={(event) => innerBoxOnClick(cardRow, inner)}
                  >{cardRow + 1}</div>
                )
              }
            </div>
          )
        }
      </main>
    </div>
  );
}

export default App;
