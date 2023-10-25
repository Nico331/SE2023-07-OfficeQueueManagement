import '../../App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css';

const MainBoard = (props) => {
    return(
        <div className="counter-list">
            {props.counters.map((counter) => (
                <div className="counter m-5">
                        <h1 className="m-5 display-2 fw-bold">COUNTER {counter.counterID}</h1>
                        <h1 className="m-5">now serving:</h1>
                        <h1 className="m-5">{counter.nextCustomer}  {counter.service}</h1>
                </div>
            ))}
        </div>
    )
}

export default MainBoard;
