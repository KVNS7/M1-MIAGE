from pathlib import Path
import pandas as pd
import re
import sys

# === CONFIGURATION ============================================================

# 1) CSV to analyse — change if necessary
INPUT_CSV = "AutoDev2.csv"
OUTPUT_CSV = "AutoDev3.csv"

# 2) Eliminatory words (case-insensitive, matched anywhere in the title).
#    Feel free to add/remove items to suit your topic.
ELIMINATORY_WORDS = [
    "philosophy",
    "sociology",
    "ethics",
    "political",
    "policy",
    "society",
    "healthcare",
    "clinical",
    "gender",
    "history",
    "art ",
    "religion",
    "linguistics",
    "psychology",
]

# ==============================================================================

def load_csv(path: Path) -> pd.DataFrame:
    if not path.exists():
        sys.exit(f"❌ Fichier introuvable : {path.resolve()}")
    try:
        return pd.read_csv(path)
    except Exception as exc:
        sys.exit(f"❌ Erreur de lecture du CSV : {exc}")


def build_regex(words: list[str]) -> re.Pattern[str]:
    """Construit une regex OR insensible à la casse, délimitée par des frontières de mot."""
    escaped = [re.escape(w) for w in words]
    pattern = r"\b(" + "|".join(escaped) + r")\b"
    return re.compile(pattern, flags=re.IGNORECASE)


def matched_words(title: str, regex: re.Pattern[str]) -> list[str]:
    """Renvoie la liste unique (minuscule) des mots éliminatoires trouvés dans le titre."""
    return sorted({m.group(0).lower() for m in regex.finditer(title)})


def main() -> None:
    df = load_csv(Path(INPUT_CSV))

    if "Title" not in df.columns:
        sys.exit("❌ Colonne « Title » absente dans le CSV.")

    regex = build_regex(ELIMINATORY_WORDS)

    titles = df["Title"].fillna("")
    off_topic_mask = titles.str.contains(regex)

    off_topic_df = df.loc[off_topic_mask, ["Title"]].copy()
    off_topic_df["Matched Words"] = titles[off_topic_mask].apply(
        lambda t: ", ".join(matched_words(t, regex))
    )

    # -----------------------------------------------------------------------
    # Affichage du bilan
    # -----------------------------------------------------------------------
    total = len(df)
    off_topic_count = len(off_topic_df)
    on_topic_count = total - off_topic_count

    print("🔎 Mots éliminatoires :", ", ".join(ELIMINATORY_WORDS))
    print(f"📄 Articles analysés  : {total}")
    print(f"🚫 Hors-sujet trouvés : {off_topic_count}")
    print(f"✅ Conservés          : {on_topic_count}")

    if off_topic_count:
        print("\nTitres hors-sujet (mot déclencheur) :")
        for _, row in off_topic_df.iterrows():
            print(f" • {row['Title']}  →  [{row['Matched Words']}]")

    # -----------------------------------------------------------------------
    # Écriture du CSV filtré (même structure, sans hors-sujet)
    # -----------------------------------------------------------------------
    on_topic_df = df[~off_topic_mask]
    try:
        on_topic_df.to_csv(OUTPUT_CSV, index=False)
        print(f"\n💾 CSV filtré écrit : {Path(OUTPUT_CSV).resolve()}")
    except Exception as exc:
        sys.exit(f"❌ Erreur lors de l’écriture du CSV filtré : {exc}")


if __name__ == "__main__":
    main()