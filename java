
let students = [
  {
    firstName: "Ahmed",
    lastName: "El Amrani",
    id: "ET001",
    email: "ahmed@gmail.com",
    department: "Informatique"
  },
  {
    firstName: "Sara",
    lastName: "Benali",
    id: "ET002",
    email: "sara@gmail.com",
    department: "Réseaux"
  },
  {
    firstName: "Youssef",
    lastName: "Idrissi",
    id: "ET003",
    email: "youssef@gmail.com",
    department: "Gestion"
  }
];

const form = document.getElementById("studentForm");
const list = document.getElementById("studentList");
const search = document.getElementById("search");

function updateApp() {
  const term = search.value.toLowerCase();

  const filtered = students.filter(s =>
    `${s.firstName} ${s.lastName} ${s.id} ${s.email}`
      .toLowerCase().includes(term)
  );

  list.innerHTML = filtered.map(s => `
    <tr>
      <td>${s.firstName} ${s.lastName}</td>
      <td>${s.id}</td>
      <td>${s.email}</td>
      <td>${s.department}</td>
      <td>
        <div class="actions">
          <button onclick="editStudent('${s.id}')">Modifier</button>
          <button class="delete"
            onclick="deleteStudent('${s.id}')">Supprimer</button>
        </div>
      </td>
    </tr>
  `).join("");

  document.getElementById("empty").textContent =
    filtered.length ? "" : "Aucun étudiant trouvé.";

  document.getElementById("total").textContent = students.length;

  document.getElementById("informatique").textContent =
    students.filter(s => s.department === "Informatique").length;

  document.getElementById("reseaux").textContent =
    students.filter(s => s.department === "Réseaux").length;
}

form.addEventListener("submit", function(e) {
  e.preventDefault();

  const student = {
    firstName: document.getElementById("firstName").value.trim(),
    lastName: document.getElementById("lastName").value.trim(),
    id: document.getElementById("studentId").value.trim(),
    email: document.getElementById("email").value.trim(),
    department: document.getElementById("department").value
  };

  if (students.some(s => s.id === student.id)) {
    alert("Ce numéro étudiant existe déjà !");
    return;
  }

  students.push(student);
  form.reset();
  updateApp();
  alert("Étudiant ajouté avec succès !");
});

function deleteStudent(id) {
  if (confirm("Voulez-vous vraiment supprimer cet étudiant ?")) {
    students = students.filter(s => s.id !== id);
    updateApp();
  }
}

function editStudent(id) {
  const s = students.find(s => s.id === id);
  if (!s) return;

  const first = prompt("Prénom :", s.firstName);
  if (first === null) return;

  const last = prompt("Nom :", s.lastName);
  if (last === null) return;

  const email = prompt("Email :", s.email);
  if (email === null) return;

  s.firstName = first.trim();
  s.lastName = last.trim();
  s.email = email.trim();

  updateApp();
}

search.addEventListener("input", updateApp);

updateApp();
