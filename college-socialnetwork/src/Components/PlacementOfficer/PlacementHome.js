
import { Link } from "react-router-dom";

function PlacementHome(){

return (

 <>

 <div className="container-fluid-main-admin">

 <h1> Welcome to the Placement Officer Page

{/* <img src={f} alt='' /> */}

</h1>

 </div>

 <>

<div className="admin-func">

<br />

 <Link to="/placement">

 <button class="btn btn-outline-success">Add Student Details</button>

 </Link><br />

 <br />

 <br />



 <div>

 <Link to="/students/allstudents">

 <button class="btn btn-outline-dark">View All Student Details</button>

 </Link></div>

 <br />



</div>

 </>

 </>

);

}

export default PlacementHome;






