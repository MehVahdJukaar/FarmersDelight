- Update mixins to allow for other modifications to placements of crops.
  - Added two tags to determine whether a crop should:
  - `farmersdelight:does_not_survive/rich_soil` Does not survive on rich soil.
    - Should contain plants considered crops, nether or water.
    - For example: Wheat, Nether Wart, Lily Pad.
  - `farmersdelight:survives/rich_soil_farmland` Survives on rich soil.
    - Should contain plants considered crops or plains.
    - For example: Tomatoes, Saplings, Fern.
- This should also make catching edge cases with these features easier to catch going forth.

Apologies for making such a breaking change super late into 1.20.1's lifespan. I'll try not to make it happen again.