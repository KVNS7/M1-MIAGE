import pandas as pd

df = pd.read_csv("Agents1.csv")
type_counts = df["Item Type"].value_counts()
print(type_counts)

from pathlib import Path
import sys


# === À PERSONNALISER SI BESOIN =================================================
IN_CSV  = "Agents1.csv"          # fichier Zotero original
OUT_CSV = "Agents2.csv"   # fichier filtré à créer
# ==============================================================================

def main() -> None:
    in_path  = Path(IN_CSV)
    out_path = Path(OUT_CSV)

    if not in_path.exists():
        sys.exit(f"Fichier introuvable : {in_path.resolve()}")

    try:
        df = pd.read_csv(in_path)
    except Exception as exc:
        sys.exit(f"Erreur de lecture du CSV : {exc}")

    # Filtrage : on garde tout sauf 'report' et 'document'
    filtered_df = df[~df["Item Type"].isin(["report", "document"])]

    try:
        filtered_df.to_csv(out_path, index=False)
    except Exception as exc:
        sys.exit(f"Erreur d’écriture du CSV filtré : {exc}")

    removed = len(df) - len(filtered_df)
    print(f"✔️  CSV filtré écrit : {out_path.resolve()}\n"
          f"   → {removed} lignes supprimées.")

if __name__ == "__main__":
    main()