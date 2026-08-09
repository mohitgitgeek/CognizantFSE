/* JavaScript exercises: data, conditionals, DOM, events, async, storage, and Fetch. */
console.log('Welcome to the Community Portal');
let formDirty = false;
const events = [
  { id: 1, name: 'Music in the Park', category: 'music', city: 'New York', date: '2026-09-15', seats: 12 },
  { id: 2, name: 'Web Accessibility Workshop', category: 'workshop', city: 'Chicago', date: '2026-10-02', seats: 8 },
  { id: 3, name: 'AI for Everyone', category: 'tech', city: 'Los Angeles', date: '2026-10-18', seats: 0 },
  { id: 4, name: 'Past event', category: 'music', city: 'New York', date: '2020-01-01', seats: 10 }
];
const list = document.querySelector('#eventList'); const filter = document.querySelector('#categoryFilter'); const search = document.querySelector('#search');
function upcomingWithSeats(event) { return new Date(event.date) >= new Date().setHours(0,0,0,0) && event.seats > 0; }
function renderEvents(items = events) { list.innerHTML = ''; items.filter(upcomingWithSeats).forEach(event => { const card = document.createElement('div'); card.className = 'col-12 col-md-6 col-lg-4'; card.innerHTML = `<article class="eventCard h-100"><h3>${event.name}</h3><p class="text-muted">${event.city} · ${event.date}</p><p>${event.seats} seats available</p><button class="btn btn-primary register-event" data-id="${event.id}">Register</button> <button class="btn btn-outline-secondary cancel-event" data-id="${event.id}">Cancel</button></article>`; list.append(card); }); }
function filterEventsByCategory(category, callback = renderEvents) { const copy = [...events]; callback(category === 'all' ? copy : copy.filter(e => e.category === category)); }
function makeRegistrationCounter() { let total = 0; return () => ++total; } const countRegistration = makeRegistrationCounter();
function registerUser(id) { try { const event = events.find(e => e.id === id); if (!event || event.seats < 1) throw new Error('That event is no longer available.'); event.seats--; alert(`Registered for ${event.name}. Category registrations: ${countRegistration()}`); renderEvents(); } catch (error) { alert(error.message); } }
function addEvent(event) { events.push(event); renderEvents(); }
list.addEventListener('click', e => { const id = Number(e.target.dataset.id); if (e.target.matches('.register-event')) registerUser(id); if (e.target.matches('.cancel-event')) alert('Registration cancelled.'); });
filter.onchange = () => filterEventsByCategory(filter.value); search.addEventListener('keydown', () => setTimeout(() => renderEvents(events.filter(e => e.name.toLowerCase().includes(search.value.toLowerCase()))), 0));
const form = document.querySelector('#registrationForm'); form.addEventListener('input', () => formDirty = true);
document.querySelector('#phone').onblur = e => document.querySelector('#phoneError').textContent = e.target.value && !/^\d{10}$/.test(e.target.value) ? 'Enter a 10 digit phone number.' : '';
document.querySelector('#eventType').onchange = e => { document.querySelector('#fee').textContent = e.target.selectedOptions[0]?.dataset.fee ? `Fee: ${e.target.selectedOptions[0].dataset.fee}` : ''; };
document.querySelector('#message').onkeydown = e => setTimeout(() => document.querySelector('#charCount').textContent = `${e.target.value.length} characters`, 0);
form.addEventListener('submit', async e => { e.preventDefault(); const { name, email, event } = form.elements; const error = document.querySelector('#formError'); error.textContent = ''; if (!name.value.trim() || !email.validity.valid || !event.value) { error.textContent = 'Please enter a valid name, email and event.'; return; } try { await postRegistration({ name: name.value, email: email.value, event: event.value }); document.querySelector('#confirmation').textContent = `Thanks ${name.value}; your place is reserved.`; formDirty = false; } catch { error.textContent = 'Registration could not be sent; please retry.'; } });
function postRegistration(data) { return new Promise((resolve, reject) => setTimeout(() => fetch('https://jsonplaceholder.typicode.com/posts', { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(data) }).then(r => r.ok ? resolve(r) : reject()).catch(reject), 400)); }
document.querySelector('#savePreference').onclick = () => { localStorage.setItem('preferredEvent', document.querySelector('#eventType').value); sessionStorage.setItem('visited', 'true'); };
document.querySelector('#clearPreference').onclick = () => { localStorage.clear(); sessionStorage.clear(); document.querySelector('#eventType').value = ''; };
document.querySelector('#eventType').value = localStorage.getItem('preferredEvent') || '';
document.querySelector('#locate').onclick = () => navigator.geolocation.getCurrentPosition(p => document.querySelector('#locationResult').textContent = `Coordinates: ${p.coords.latitude.toFixed(4)}, ${p.coords.longitude.toFixed(4)}`, err => document.querySelector('#locationResult').textContent = `Location error: ${err.message}`, { enableHighAccuracy: true, timeout: 10000 });
async function loadMockEvents() { document.querySelector('#loading').classList.remove('d-none'); try { await fetch('https://jsonplaceholder.typicode.com/todos/1').then(r => r.json()); } catch (error) { console.warn('Mock API unavailable', error); } finally { document.querySelector('#loading').classList.add('d-none'); } }
window.addEventListener('load', () => { alert('Community portal loaded.'); renderEvents(); loadMockEvents(); });
