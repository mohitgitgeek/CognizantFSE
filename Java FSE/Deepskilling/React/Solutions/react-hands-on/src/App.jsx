import { Component, createContext, useContext, useState } from 'react';

const ThemeContext = createContext('light');
const offices = [
  { name: 'DBS', rent: 50000, address: 'Chennai' },
  { name: 'WeWork', rent: 75000, address: 'Bengaluru' }
];

function StudentPortal() {
  return <section><h1>Welcome to the Home page of Student Management Portal</h1><p>Welcome to the About page of the Student Management Portal</p><p>Welcome to the Contact page of the Student Management Portal</p></section>;
}

function CalculateScore({ name = 'Student', school = 'Cognizant Academy', total = 450, goal = 5 }) {
  return <section><h2>{name}</h2><p>{school}: average score {(total / goal).toFixed(2)}</p></section>;
}

class CountPeople extends Component {
  state = { entrycount: 0, exitcount: 0 };
  updateEntry = () => this.setState(({ entrycount }) => ({ entrycount: entrycount + 1 }));
  updateExit = () => this.setState(({ exitcount }) => ({ exitcount: exitcount + 1 }));
  render() { return <section><h2>Mall counter</h2><p>Entered: {this.state.entrycount}; Exited: {this.state.exitcount}</p><button onClick={this.updateEntry}>Login</button><button onClick={this.updateExit}>Exit</button></section>; }
}

function OfficeSpaceRental() {
  return <section><h2>Office Space Rental</h2><img alt="Office space" width="240" src="https://images.unsplash.com/photo-1497366754035-f200968a6e72?auto=format&fit=crop&w=480&q=80" />{offices.map(office => <article key={office.name}><h3>{office.name}</h3><p style={{ color: office.rent < 60000 ? 'red' : 'green' }}>Rent: ₹{office.rent}</p><p>{office.address}</p></article>)}</section>;
}

function Events() {
  const [count, setCount] = useState(0); const [rupees, setRupees] = useState(1000);
  const increment = () => { setCount(n => n + 1); alert('Hello! Counter increased.'); };
  return <section><h2>Event examples</h2><p>Counter: {count}</p><button onClick={increment}>Increment</button><button onClick={() => setCount(n => n - 1)}>Decrement</button><button onClick={() => alert('Welcome')}>Say Welcome</button><button onClick={event => alert(`I was clicked (${event.type})`)}>OnPress</button><p>₹{rupees} = €{(rupees / 90).toFixed(2)}</p><button onClick={() => setRupees(rupees + 100)}>Convert</button></section>;
}

function TicketBooking() {
  const [loggedIn, setLoggedIn] = useState(false);
  return <section><h2>Flight details: Chennai → London</h2>{loggedIn ? <><p>Welcome, user. You can book tickets.</p><button onClick={() => setLoggedIn(false)}>Logout</button></> : <><p>Guests can browse. Please log in to book.</p><button onClick={() => setLoggedIn(true)}>Login</button></>}</section>;
}

function EmployeeCard() { const theme = useContext(ThemeContext); return <button className={theme}>Employee card ({theme} theme)</button>; }
function ThemeDemo() { const [theme, setTheme] = useState('light'); return <ThemeContext.Provider value={theme}><section><h2>Context API</h2><button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>Toggle theme</button><EmployeeCard /></section></ThemeContext.Provider>; }

function ComplaintRegister() {
  const [name, setName] = useState(''); const [complaint, setComplaint] = useState('');
  const submit = e => { e.preventDefault(); if (name && complaint) alert(`Complaint registered. Reference: ${crypto.randomUUID().slice(0, 8)}`); };
  return <form onSubmit={submit}><h2>Raise a complaint</h2><input required value={name} onChange={e => setName(e.target.value)} placeholder="Employee name" /><textarea required value={complaint} onChange={e => setComplaint(e.target.value)} placeholder="Complaint" /><button>Submit</button></form>;
}

function Register() {
  const [form, setForm] = useState({ name: '', email: '', password: '' });
  const errors = { name: form.name.length >= 5 ? '' : 'Name must have at least 5 characters', email: /^[^@]+@[^.]+\..+$/.test(form.email) ? '' : 'Enter a valid email', password: form.password.length >= 8 ? '' : 'Password must have at least 8 characters' };
  const submit = e => { e.preventDefault(); if (!Object.values(errors).some(Boolean)) alert('Registered successfully'); };
  return <form onSubmit={submit}><h2>Mail registration</h2>{Object.keys(form).map(key => <label key={key}>{key}<input type={key === 'password' ? 'password' : 'text'} value={form[key]} onChange={e => setForm({ ...form, [key]: e.target.value })} />{errors[key] && <small>{errors[key]}</small>}</label>)}<button>Register</button></form>;
}

function Cricket() {
  const players = [{ name: 'Rohit', score: 90 }, { name: 'Virat', score: 65 }, { name: 'Gill', score: 72 }];
  return <section><h2>Cricket app</h2><p>Below 70: {players.filter(p => p.score < 70).map(p => p.name).join(', ')}</p><p>Players: {players.map(p => `${p.name} (${p.score})`).join(', ')}</p></section>;
}

export default function App() {
  return <main><header><h1>React Hands-on Solutions</h1><p>Examples for labs 1–17. Each component is independent and can be moved into its named create-react-app project.</p></header><StudentPortal /><CalculateScore /><CountPeople /><Cricket /><OfficeSpaceRental /><Events /><TicketBooking /><ThemeDemo /><ComplaintRegister /><Register /></main>;
}
