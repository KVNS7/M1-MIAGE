#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
generic_scoring.py
──────────────────
Attribue un score 0…N aux abstracts suivant la présence de paquets de termes
(poids = +1 par paquet trouvé). Affiche le nombre d’articles pour chaque score
et exporte en CSV les articles à 3 et 2 points.
"""

from pathlib import Path
import pandas as pd
import re
import sys
from collections import Counter

# ---------------------------------------------------------------------------
# USER CONFIGURATION  🔧
# ---------------------------------------------------------------------------

CSV_PATH = "LLM3.csv"      # Chemin vers le fichier CSV à analyser
OUTPUT_3POINTS = "LLM_3pts.csv"
OUTPUT_2POINTS = "LLM_2pts.csv"


SCORING_SCHEMES = {
    "llm_definition": [
        ["large language model", "llm", "foundation model", "foundational model"],
        ["definition", "taxonomy", "survey", "systematic review"],
        ["natural language processing", "nlp", "transformer-based"],
    ],
}

QUERY_KEY = "llm_definition"        # Choisis la clé du schéma à appliquer
# ---------------------------------------------------------------------------


def compile_package_regex(packages: list[list[str]]) -> list[re.Pattern]:
    """Compile un motif regex par paquet, insensible à la casse + frontières de mot."""
    regexes = []
    for pkg in packages:
        escaped = [re.escape(t) for t in pkg]
        pattern = r"\b(" + "|".join(escaped) + r")\b"
        regexes.append(re.compile(pattern, flags=re.IGNORECASE))
    return regexes


def main() -> None:
    # --- Chargement CSV ------------------------------------------------------
    csv_path = Path(CSV_PATH)
    if not csv_path.exists():
        sys.exit(f"❌ Fichier introuvable : {csv_path.resolve()}")

    try:
        df = pd.read_csv(csv_path)
    except Exception as exc:
        sys.exit(f"❌ Erreur de lecture CSV : {exc}")

    if "Abstract Note" not in df.columns:
        sys.exit("❌ Colonne « Abstract Note » absente dans le CSV.")

    # --- Préparation des paquets & regex ------------------------------------
    if QUERY_KEY not in SCORING_SCHEMES:
        sys.exit(f"❌ QUERY_KEY inconnu : {QUERY_KEY}")

    packages = SCORING_SCHEMES[QUERY_KEY]
    regexes = compile_package_regex(packages)
    max_score = len(packages)

    # --- Scoring et collecte des scores -------------------------------------
    counts = Counter()      # {score: nb d’articles}
    missing_count = 0       # abstracts vides / manquants
    scores = []             # liste des scores pour chaque ligne

    for abstract in df["Abstract Note"].astype(str):
        txt = abstract.strip()
        if txt == "" or txt.lower() == "nan":
            missing_count += 1
            scores.append(None)
            continue

        score = sum(bool(r.search(txt)) for r in regexes)
        counts[score] += 1
        scores.append(score)

    # on ajoute la colonne Score au DataFrame
    df["Score"] = scores

    # --- Export des subsets à 3 et 2 points -------------------------------
    out_dir = csv_path.parent
    file_3 = out_dir / f"{OUTPUT_3POINTS}"
    file_2 = out_dir / f"{OUTPUT_2POINTS}"

    df[df["Score"] == max_score].to_csv(file_3, index=False)
    df[df["Score"] == max_score - 1].to_csv(file_2, index=False)

    # --- Affichage -----------------------------------------------------------
    print(f"📝 Distribution des scores pour « {QUERY_KEY} » (max {max_score})")
    for s in range(max_score, -1, -1):
        print(f" {s} point(s) : {counts.get(s, 0)} article(s)")
    print(f" ❓ abstract manquant : {missing_count} article(s)\n")
    print(f"✅ Articles à {max_score} points exportés dans : {file_3}")
    print(f"✅ Articles à {max_score-1} points exportés dans : {file_2}")


if __name__ == "__main__":
    main()
