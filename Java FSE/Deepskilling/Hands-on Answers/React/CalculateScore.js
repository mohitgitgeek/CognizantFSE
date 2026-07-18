import React from 'react';
import '../Stylesheets/mystyles.css';

const percentToDecimal = (decimal) => {
    return (decimal.toFixed(2) + '%')
}

const CalcScore = (total , goal) => {
    return percentToDecimal(total/goal)
}

export const CalculateScore = ({Name, school, total, goal}) =>(
<div className="formatstyle">
<h1 style={{color: 'Brown'}} >Student Details :</h1>
<div className="Name">
<b> <span> Name: </span> </b>
<span>{Name}</span>
</div>
<div className="School">
<b> <span> School: </span> </b>
<span>{school}</span>
</div>
<div className="Total">
<b> <span>Total :</span></b>
<span>{total}</span>
<span>Marks</span>
</div>
<div className="Score">
<b>Score :</b>
<span>
{CalcScore(
total,
goal
)}
</span>
</div>
</div>)