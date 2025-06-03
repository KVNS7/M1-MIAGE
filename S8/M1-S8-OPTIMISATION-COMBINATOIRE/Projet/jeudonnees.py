from dataclasses import dataclass
import random, time, copy, multiprocessing
import pandas as pd
from typing import List

# =======================
# === Variables 
# =======================

TIMEOUT = 1                   # Timeout en secondes

nb_objets_depart = 5            # Nombre d'objets par jeu de données au départ
nb_objets = nb_objets_depart    # Nombre d'objets actuels
nb_objets_max = 1000            # Nombre d'objets max (a la fin des itérations)
iterations_par_nb = 2           # Nombre de jeu de données de taille nb_objets

poids_max = 20                  # Poids max du sac à dos
volume_max = 18                 # Volume max du sac à dos

# =======================
# === Classes 
# =======================

class Element:
    id: int
    poids: int
    volume: int
    valeur: int
    compteur = 0
    
    def __init__(self, poids, volume, valeur):
        self.id = Element.compteur
        Element.compteur += 1
        self.poids = poids
        self.volume = volume
        self.valeur = valeur
        
    def afficher(self):
        print(f"\nid : {self.id}  |  Poids : {self.poids}  |  Volume : {self.volume}  |  Valeur : {self.valeur}")
        
    def reset_compteur(self):
        Element.compteur = 0

@dataclass
class Etat:
    idx: int
    poids_rem: int
    vol_rem: int
    val_courante: float
    choix: List[Element]

@dataclass
class Result:
    valeur: float
    choix: List[Element]

# =======================
# === Fonctions 
# =======================

def generer_elements(nb_elements, poids_max, volume_max):   # Génère aléatoirement un jeu de données de taille nb_elements
    liste = []
    for _ in range (nb_elements):
        poids = random.randint(1, int((poids_max//2) + poids_max*0.1))
        volume = random.randint(1, int((volume_max//2) + volume_max*0.1))
        valeur = random.randint(2*(poids_max + volume_max), 4*(poids_max + volume_max))
        
        element = Element(poids, volume, valeur)
        liste.append(element)
    
    return liste

def afficher_liste(liste):  # Affiche le jeu de données
    for i in liste:
        i.afficher()

# =======================
# === Méthode arborescente 
# =======================

def trier_elements(elements): #trie les element selon wi/(pi+vi) decroissant
    return sorted(elements, key=lambda e: e.valeur / (e.poids + e.volume), reverse=True)

def calcule_minorant_h3(elements, poids_max, volume_max): #heuristique h'3 determinee en cours
    elements = trier_elements(elements)
    poids = volume = 0
    valeur = 0
    for e in elements:
        p, v = e.poids, e.volume
        if poids + p <= poids_max and volume + v <= volume_max:
            poids += p
            volume += v
            valeur += e.valeur
    return valeur

def calcule_majorant_h4(elements, poids_max, volume_max): #heuristique h4 determinee en cours
    capacite = poids_max + volume_max
    elements = trier_elements(elements)
    poids_virtuel = 0
    valeur = 0
    for e in elements:
        taille = e.poids + e.volume
        if poids_virtuel + taille <= capacite:
            poids_virtuel += taille
            valeur += e.valeur
        else:
            reste = capacite - poids_virtuel
            if reste > 0:
                ratio = reste / taille
                valeur += e.valeur * ratio
            break
    return valeur

def methode_arbo(elements: List[Element], poids_max, volume_max) -> Result: 
    elements = trier_elements(elements)

    best_val = 0  # Valeur optimale trouvée jusqu'ici
    best_choix = []  # Liste des éléments choisis correspondant à best_val

    # Pile d'états à explorer (parcours en profondeur)
    stack = [Etat(0, poids_max, volume_max, 0, [])]

    while stack:
        s = stack.pop()

        # Cas terminal : tous les éléments ont été traités
        if s.idx == len(elements):
            if s.val_courante > best_val:
                best_val = s.val_courante
                best_choix = copy.deepcopy(s.choix)
            continue

        # Eléments restants à explorer
        reste = elements[s.idx:]

        # Majorant
        maj = s.val_courante + calcule_majorant_h4(reste, s.poids_rem, s.vol_rem)
        if maj <= best_val:
            continue  # Élague cette branche si le majorant <= best_val

        # Minorant 
        minorant = s.val_courante + calcule_minorant_h3(reste, s.poids_rem, s.vol_rem)
        if minorant > best_val:
            best_val = minorant
            best_choix = copy.deepcopy(s.choix)  # Met à jour la solution si le minorant est meilleur

        # Récupère l'élément courant
        e = elements[s.idx]
        p, v = int(e.poids), int(e.volume)

        # Cas 1 : Exclure l'élément courant
        stack.append(Etat(s.idx + 1, s.poids_rem, s.vol_rem, s.val_courante, list(s.choix)))

        # Cas 2 : Inclure l'élément courant si faisable
        if p <= s.poids_rem and v <= s.vol_rem:
            new_choix = list(s.choix)
            new_choix.append(e)
            stack.append(Etat(
                s.idx + 1,
                s.poids_rem - p,
                s.vol_rem - v,
                s.val_courante + e.valeur,
                new_choix
            ))

    return Result(best_val, best_choix)

# =======================
# === Programmation Dynamique 
# =======================

def methode_prog_dyn(elements: List[Element], P: int, V: int, n:int)->Result:
    
    # Création du tableau TF[p][v][k] : 3D
    TF = [[[0 for _ in range(n + 1)] for _ in range(V + 1)] for _ in range(P + 1)]

    # Initialisation de la dernière couche (k = n)
    dernier = elements[n - 1]
    for p in range(dernier.poids, P + 1):
        for v in range(dernier.volume, V + 1):
            TF[p][v][n] = dernier.valeur

    # Remplissage du tableau TF
    for k in range(n - 1, 0, -1):
        e = elements[k - 1]
        for p in range(P + 1):
            for v in range(V + 1):
                if p < e.poids or v < e.volume:
                    TF[p][v][k] = TF[p][v][k + 1]
                else:
                    TF[p][v][k] = max(
                        e.valeur + TF[p - e.poids][v - e.volume][k + 1],
                        TF[p][v][k + 1]
                    )

    # Reconstitution de la solution optimale
    p, v = P, V
    objets_choisis = []
    for k in range(1, n):
        if TF[p][v][k] != TF[p][v][k + 1]:
            objets_choisis.append(elements[k - 1])
            p -= elements[k - 1].poids
            v -= elements[k - 1].volume

    # On n'oublie pas de tester si le dernier élément est pris
    if TF[p][v][n] != TF[p][v][n - 1]:
        objets_choisis.append(dernier)

    valeur_totale = sum(e.valeur for e in objets_choisis)

    return Result(valeur_totale, objets_choisis)

# =======================
# === Gestion timeout 
# =======================

# worker global pour multiprocessing (picklable)
def _worker(q, func, args):
    q.put(func(*args))

def run_with_timeout(func, args, timeout):
    """Exécute func(*args) et renvoie son résultat, ou None si timeout."""
    q = multiprocessing.Queue()
    p = multiprocessing.Process(target=_worker, args=(q, func, args))
    p.start()
    p.join(timeout)
    if p.is_alive():
        p.terminate()
        p.join()
        return None
    return q.get() if not q.empty() else None

# =======================
# === Main 
# =======================

if __name__ == "__main__":
    multiprocessing.set_start_method('spawn', force=True)

    temps_algo_arbo = []
    temps_algo_dyn  = []

    for _ in range(nb_objets_depart, nb_objets_max+1):
        for _ in range(iterations_par_nb):
            liste_objets = generer_elements(nb_objets, poids_max, volume_max)

            # Arborescent
            chrono_debut = time.perf_counter()
            res_arbo = run_with_timeout(methode_arbo, (liste_objets, poids_max, volume_max), TIMEOUT)
            chrono_fin = time.perf_counter()
            temps_algo_arbo.append({
                "nb_objets":    nb_objets,
                "temps_arbo":   chrono_fin - chrono_debut,
                "timeout_arbo": res_arbo is None,
            })

            # Dynamique
            chrono_debut = time.perf_counter()
            res_dyn = run_with_timeout(methode_prog_dyn, (liste_objets, poids_max, volume_max, nb_objets), TIMEOUT)
            chrono_fin = time.perf_counter()
            temps_algo_dyn.append({
                "temps_dyn":    chrono_fin - chrono_debut,
                "timeout_dyn":  res_dyn is None,
            })

        nb_objets += 1

    # Construction du DataFrame final avec indicateur timeout
    df_arbo = pd.DataFrame(temps_algo_arbo)
    df_dyn  = pd.DataFrame(temps_algo_dyn)
    df_all = pd.concat([
        df_arbo[["nb_objets", "temps_arbo", "timeout_arbo"]],
        df_dyn[       ["temps_dyn", "timeout_dyn"]]
    ], axis=1)
    df_all["timeout"] = df_all["timeout_arbo"] | df_all["timeout_dyn"]
    df_all = df_all.drop(columns=["timeout_arbo", "timeout_dyn"])

    df_all.to_csv("comparaison_algos.csv", index=False)
    df_all.to_excel("comparaison_algos.xlsx", index=False)

    print("Fichier comparaison_algos.csv généré")